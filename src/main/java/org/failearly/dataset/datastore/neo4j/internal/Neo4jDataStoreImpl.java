/*
 * dataSet - Test Support For Data Stores.
 *
 * Copyright (C) 2014-2015 Marko Umek (http://fail-early.com/contact)
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

package org.failearly.dataset.datastore.neo4j.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.failearly.dataset.datastore.DataStoreException;
import org.failearly.dataset.datastore.neo4j.internal.json.Neo4JResults;
import org.failearly.dataset.datastore.neo4j.internal.json.Neo4JStatements;
import org.failearly.dataset.datastore.support.SimpleFileTransactionalSupportDataStoreBase;
import org.failearly.dataset.resource.DataResource;
import org.failearly.dataset.simplefile.SimpleFileStatement;
import org.failearly.dataset.util.ExtendedProperties;
import org.failearly.dataset.util.With;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Neo4jDataStoreImpl is responsible for ...
 */
public final class Neo4jDataStoreImpl extends SimpleFileTransactionalSupportDataStoreBase<Neo4JStatements> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Neo4jDataStoreImpl.class);

    public static final String DATASTORE_NEO4J_URL = "neo4j.url";
    public static final String DATASTORE_NEO4J_USER = "neo4j.user";
    public static final String DATASTORE_NEO4J_PASSWORD = "neo4j.password";

    private String url;
    private WebTarget webTarget;

    public Neo4jDataStoreImpl(String dataStoreId, String dataStoreConfig) {
        super(dataStoreId, dataStoreConfig);
    }

    @Override
    protected void doInitialize(ExtendedProperties properties) throws DataStoreException {
        this.url = properties.getMandatoryProperty(DATASTORE_NEO4J_URL);
        this.webTarget = ClientBuilder.newClient().target(this.url);
        with.action("Check running Neo4J server on " + this.url, () -> {
            final Response response = executePostRequest(Neo4JStatements.NO_STATEMENTS);
            checkHttpStatus(response);
        });
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
        if( statusInfo.getFamily() != Response.Status.Family.SUCCESSFUL ) {
            throw new Neo4JHTTPErrorException(response);
        }
    }

    private void checkNeo4jErrors(Response response) throws IOException {
        final String resultsString = response.readEntity(String.class);
        LOGGER.debug("Neo4J Results:\n{}", resultsString);
        final Neo4JResults results = Neo4JResults.fromJson(resultsString);
        results.throwOnErrors();
    }
}
