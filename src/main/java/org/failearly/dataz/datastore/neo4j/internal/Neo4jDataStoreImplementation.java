/*
 * dataZ - Test Support For Data Stores.
 *
 * Copyright 2014-2020 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.failearly.dataz.datastore.neo4j.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.failearly.dataz.internal.common.message.Message;
import org.failearly.dataz.internal.common.proputils.PropertiesAccessor;
import org.failearly.dataz.NamedDataStore;
import org.failearly.dataz.datastore.DataStoreBase;
import org.failearly.dataz.datastore.DataStoreException;
import org.failearly.dataz.datastore.neo4j.Neo4JConfigProperties;
import org.failearly.dataz.datastore.neo4j.Neo4jDataStore;
import org.failearly.dataz.datastore.neo4j.internal.json.Neo4JResponse;
import org.failearly.dataz.datastore.neo4j.internal.json.Neo4JStatements;
import org.failearly.dataz.datastore.support.simplefile.SimpleFileParser;
import org.failearly.dataz.datastore.support.simplefile.SimpleFileStatement;
import org.failearly.dataz.datastore.support.simplefile.StatementProcessor;
import org.failearly.dataz.datastore.support.transaction.*;
import org.failearly.dataz.resource.DataResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static org.failearly.dataz.datastore.support.transaction.TransactionalSupportBuilder.Provider.USE_DATA_RESOURCE_PROVIDER;
import static org.failearly.dataz.datastore.support.transaction.TransactionalSupportBuilder.Provider.USE_STATEMENT_PROVIDER;

/**
 * Neo4jDataStoreImplementation is the implementation for {@link Neo4jDataStore}.
 */
@SuppressWarnings("WeakerAccess")
public final class Neo4jDataStoreImplementation extends DataStoreBase implements Neo4JConfigProperties {
    private static final Logger LOGGER = LoggerFactory.getLogger(Neo4jDataStoreImplementation.class);

    private String url;
    private WebTarget webTarget;
    private String commitPath;

    private final TransactionalSupportBuilder<Neo4JStatements> transactionalSupportBuilder;

    public Neo4jDataStoreImplementation(Class<? extends NamedDataStore> namedDataStore, Neo4jDataStore annotation) {
        super(namedDataStore, annotation);
        transactionalSupportBuilder = TransactionalSupportBuilder.createBuilder(Neo4JStatements.class)
            .withPerDataResource(new Neo4JPerDataResourceProvider())
            .withPerStatement(new Neo4JPerStatementProvider());
    }

    @Override
    protected void doEstablishConnection(PropertiesAccessor properties) throws Exception {
        this.url = properties.getStringValue(NEO4J_URL);
        this.commitPath = properties.getStringValue(NEO4J_COMMIT_PATH, NEO4J_DEFAULT_COMMIT_PATH);

        LOGGER.info("Connect to Neo4J server using URI '{}'!", neo4jCommitUri());
        this.webTarget = ClientBuilder.newClient().target(neo4jCommitUri());
        final Response response = executePostRequest(Neo4JStatements.NO_STATEMENTS);
        checkHttpStatus(response);
    }

    @Override
    protected void doApplyResource(DataResource dataResource) throws DataStoreException {
        final TransactionalSupport<Neo4JStatements> transactionalSupport = transactionalSupportBuilder
            .withProvider(chooseProvider(dataResource))
            .build();

        transactionalSupport.process(dataResource);
    }

    private TransactionalSupportBuilder.Provider chooseProvider(DataResource dataResource) {
        return dataResource.isFailOnError() ? USE_STATEMENT_PROVIDER : USE_DATA_RESOURCE_PROVIDER;
    }



    private String neo4jCommitUri() {
        return this.url + this.commitPath;
    }

    @Override
    protected Message establishingConnectionFailedMessage() {
        return Neo4JEstablishingConnectionFailedMessage.create(mb ->
            mb.withDataStore(this)
                .withUrl(this.url)
                .withCommitPath(this.commitPath)
        );
    }

    private Response executePostRequest(Neo4JStatements statements) throws JsonProcessingException {
        return webTarget.request(MediaType.APPLICATION_JSON_TYPE)
            .accept(MediaType.APPLICATION_JSON)
            .post(Entity.entity(statements.toJson(), MediaType.APPLICATION_JSON));
    }

    private void checkHttpStatus(Response response) {
        final Response.StatusType statusInfo = response.getStatusInfo();
        LOGGER.debug("Neo4J HTTP Status: {}", statusInfo);
        if (statusInfo.getFamily() != Response.Status.Family.SUCCESSFUL) {
            throw new Neo4JHTTPErrorException(response);
        }
    }

    private abstract class Neo4JProviderBase {
        final SimpleFileParser simpleFileParser = new SimpleFileParser();

        public Neo4JStatements createTransactionContext() throws Exception {
            return new Neo4JStatements();
        }

        final void doCommit(Neo4JStatements neo4JStatements) throws Exception {
            try {
                final Response response = executePostRequest(neo4JStatements);
                checkHttpStatus(response);
                checkNeo4jErrors(response);
            } finally {
                neo4JStatements.reset();
            }
        }

        private void checkNeo4jErrors(Response response) throws IOException {
            final String resultsString = response.readEntity(String.class);
            LOGGER.debug("Neo4J Results:\n{}", resultsString);
            final Neo4JResponse results = Neo4JResponse.fromJson(resultsString);
            results.throwOnErrors();
        }

        public void close(Neo4JStatements neo4JStatements, ProcessingState processingState) throws Exception {
            neo4JStatements.reset();
        }
    }


    private class Neo4JPerStatementProvider extends Neo4JProviderBase implements PerStatementProvider<Neo4JStatements> {

        @Override
        public void process(Neo4JStatements neo4JStatements, DataResource dataResource) throws Exception {
            simpleFileParser.processStatements(dataResource, neo4JStatements, (simpleFileStatement, statements) -> {
                statements.addStatement(simpleFileStatement);
                doCommit(statements);
            });
        }
    }

    private class Neo4JPerDataResourceProvider extends Neo4JProviderBase implements PerDataResourceProvider<Neo4JStatements> {
        @Override
        public void process(Neo4JStatements neo4JStatements, DataResource dataResource) throws Exception {
            simpleFileParser.processStatements(dataResource, neo4JStatements, new StatementProcessor<Neo4JStatements>() {
                @Override
                public void process(SimpleFileStatement simpleFileStatement, Neo4JStatements neo4JStatements) throws Exception {
                    neo4JStatements.addStatement(simpleFileStatement);
                }

                @Override
                public void commit(Neo4JStatements neo4JStatements) throws Exception {
                    doCommit(neo4JStatements);
                }
            });
        }

        @Override
        public void commit(Neo4JStatements neo4JStatements) throws Exception {
            doCommit(neo4JStatements);
        }

        public void close(Neo4JStatements neo4JStatements, ProcessingState processingState) throws Exception {
            neo4JStatements.reset();
        }
    }

}
