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

package org.failearly.dataset.datastore.neo4j.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.failearly.dataset.datastore.neo4j.internal.json.Neo4JResponse;
import org.failearly.dataset.datastore.neo4j.internal.json.Neo4JStatements;
import org.failearly.dataset.datastore.support.SimpleFileTransactionalSupportDataStoreBase;
import org.failearly.dataset.resource.DataResource;
import org.failearly.dataset.simplefile.SimpleFileStatement;
import org.failearly.common.test.ExtendedProperties;
import org.failearly.common.test.mb.MessageBuilders;
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
    private static final Logger LOGGER=LoggerFactory.getLogger(Neo4jDataStoreImpl.class);

    public static final String DATASTORE_NEO4J_URL="neo4j.url";
    public static final String DATASTORE_NEO4J_USER="neo4j.user";
    public static final String DATASTORE_NEO4J_PASSWORD="neo4j.password";

    private String url;
    private WebTarget webTarget;

    public Neo4jDataStoreImpl(String dataStoreId, String dataStoreConfigFile) {
        super(dataStoreId, dataStoreConfigFile);
    }

    @Override
    protected void doEstablishConnection(ExtendedProperties properties) throws Exception {
        this.url=properties.getMandatoryProperty(DATASTORE_NEO4J_URL);
        LOGGER.info("Connect to Neo4J server using URL '{}'!", this.url);
        this.webTarget=ClientBuilder.newClient().target(this.url);
        final Response response=executePostRequest(Neo4JStatements.NO_STATEMENTS);
        checkHttpStatus(response);
    }

    // @formatter:off
    @Override
    protected MessageBuilders.LazyMessage establishingConnectionFailedMessage() {
        return MessageBuilders.createLazyMessage(mb->{
                return mb
                        .argument("url", this.url)
                        .argument("urlconfig", DATASTORE_NEO4J_URL)
                        .argument("config", getConfigFile())
                        .argument("validurl", "http://<host>:<port>/db/data/transaction/commit")
                        .argument("exampleurl", "http://localhost:7474/db/data/transaction/commit")
                        .firstLine("Can't connect to Neo4J server on '__url__'")
                        .newline()
                        .lines(
                            "DataSet is not able to connect to your Neo4J server.",
                            "",
                            "Possible fixes:"
                        )
                        .newline()
                        .sub()
                            .lines(
                                "- Download and install Neo4J.",
                                "  1. Download Neo4j from http://neo4j.com/download and ...",
                                "  2. install it. Description could be found here http://neo4j.com/docs/stable/server-installation.html"
                            )
                            .line("- Start Neo4J server by using 'neo4j start'.")
                            .lines("- Your server does not listen to expected port (default is 7474).",
                                   "  Set the '__urlconfig__' in your __config__.")
                            .lines(
                                    "- Your url is not correct. A valid Neo4J url has the format '__validurl__'.",
                                    "  Example URL: __exampleurl__"
                            )
                        .end()
                        .newline()
                        .lines(
                            "Caution: Do not share your instance of Neo4J server. Your tests should use their own instance.",
                            "  If you need an instance for manual tests, make a second installation ",
                            "  (by copying your installation) and change the port.",
                            "  More about configuration could be found here: http://neo4j.com/docs/stable/server-configuration.html"
                        )
                    .newlines(3);
            }
        );
    }
    // @formatter:on

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
        final Response response=executePostRequest(statements);
        checkHttpStatus(response);
        checkNeo4jErrors(response);
    }

    private Response executePostRequest(Neo4JStatements statements) throws JsonProcessingException {
        return webTarget.request(MediaType.APPLICATION_JSON_TYPE)
            .accept(MediaType.APPLICATION_JSON)
            .post(Entity.entity(statements.toJson(), MediaType.APPLICATION_JSON));
    }

    private void checkHttpStatus(Response response) {
        final Response.StatusType statusInfo=response.getStatusInfo();
        LOGGER.debug("Neo4J HTTP Status: {}", statusInfo);
        if (statusInfo.getFamily() != Response.Status.Family.SUCCESSFUL) {
            throw new Neo4JHTTPErrorException(response);
        }
    }

    private void checkNeo4jErrors(Response response) throws IOException {
        final String resultsString=response.readEntity(String.class);
        LOGGER.debug("Neo4J Results:\n{}", resultsString);
        final Neo4JResponse results=Neo4JResponse.fromJson(resultsString);
        results.throwOnErrors();
    }
}
