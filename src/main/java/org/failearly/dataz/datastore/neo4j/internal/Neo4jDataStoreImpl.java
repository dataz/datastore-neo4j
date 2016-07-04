/*
 * dataZ - Test Support For Data Stores.
 *
 * Copyright (C) 2014-2016 'Marko Umek' (http://fail-early.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package org.failearly.dataz.datastore.neo4j.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.failearly.common.message.Message;
import org.failearly.common.proputils.PropertiesAccessor;
import org.failearly.dataz.datastore.neo4j.Neo4JConfigProperties;
import org.failearly.dataz.datastore.neo4j.Neo4jDataStore;
import org.failearly.dataz.datastore.neo4j.internal.json.Neo4JResponse;
import org.failearly.dataz.datastore.neo4j.internal.json.Neo4JStatements;
import org.failearly.dataz.NamedDataStore;
import org.failearly.dataz.datastore.support.SimpleFileTransactionalSupportDataStoreBase;
import org.failearly.dataz.resource.DataResource;
import org.failearly.dataz.simplefile.SimpleFileStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Neo4jDataStoreImpl is the implementation for {@link Neo4jDataStore}.
 */
@SuppressWarnings("WeakerAccess")
public final class Neo4jDataStoreImpl extends SimpleFileTransactionalSupportDataStoreBase<Neo4JStatements> implements Neo4JConfigProperties {
    private static final Logger LOGGER = LoggerFactory.getLogger(Neo4jDataStoreImpl.class);

    private String url;
    private WebTarget webTarget;
    private String commitPath;

    public Neo4jDataStoreImpl(Class<? extends NamedDataStore> namedDataStore, Neo4jDataStore annotation) {
        super(namedDataStore, annotation);
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

    @Override
    protected Neo4JStatements startTransaction(DataResource dataResource, boolean useTransaction) throws Exception {
        return new Neo4JStatements();
    }

    @Override
    protected void doExecuteStatement(Neo4JStatements statements, SimpleFileStatement statement) throws Exception {
        statements.addStatement(statement);
    }

    @Override
    protected void commitTransaction(Neo4JStatements statements) throws Exception {
        final Response response = executePostRequest(statements);
        checkHttpStatus(response);
        checkNeo4jErrors(response);
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

    private void checkNeo4jErrors(Response response) throws IOException {
        final String resultsString = response.readEntity(String.class);
        LOGGER.debug("Neo4J Results:\n{}", resultsString);
        final Neo4JResponse results = Neo4JResponse.fromJson(resultsString);
        results.throwOnErrors();
    }
}
