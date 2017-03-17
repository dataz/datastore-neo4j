/*
 * dataZ - Test Support For Data Stores.
 *
 * Copyright 2014-2017 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.failearly.dataz.datastore.neo4j.internal;

import org.failearly.common.message.*;
import org.failearly.dataz.datastore.DataStoreBase;
import org.failearly.dataz.datastore.neo4j.Neo4JConfigProperties;

/**
 * Neo4JEstablishingConnectionFailedMessage is responsible for ...
 */
@ClasspathMessageTemplate
@TemplateParameters({
        Neo4JEstablishingConnectionFailedMessage.ARG_URL,
        Neo4JEstablishingConnectionFailedMessage.ARG_DS_ID,
        Neo4JEstablishingConnectionFailedMessage.ARG_COMMIT_PATH,
        Neo4JEstablishingConnectionFailedMessage.ARG_CONFIG_FILE
    })
final class Neo4JEstablishingConnectionFailedMessage extends MessageBuilderBase<Neo4JEstablishingConnectionFailedMessage> {
    static final String ARG_URL = "url";
    static final String ARG_DS_ID = "dsid";
    static final String ARG_CONFIG_FILE = "configfile";
    static final String ARG_COMMIT_PATH = "commit";

    private static final String ARG_URL_PROPERTY = "urlproperty";
    private static final String ARG_COMMIT_PROPERTY = "commitproperty";
    private static final String ARG_DEFAULT_COMMIT_VALUE = "defaultcommit";

    private Neo4JEstablishingConnectionFailedMessage() {
        super(Neo4JEstablishingConnectionFailedMessage.class);
    }

    static Message create(MessageBuilder.Initializer<Neo4JEstablishingConnectionFailedMessage> initializer) {
        return new Neo4JEstablishingConnectionFailedMessage().buildLazyMessage(initializer);
    }

    @Override
    protected void standardArgumentsHook(MessageArguments messageArguments) {
        messageArguments.addMandatoryArgument(ARG_COMMIT_PROPERTY, Neo4JConfigProperties.NEO4J_COMMIT_PATH);
        messageArguments.addMandatoryArgument(ARG_DEFAULT_COMMIT_VALUE, Neo4JConfigProperties.NEO4J_DEFAULT_COMMIT_PATH);
        messageArguments.addMandatoryArgument(ARG_URL_PROPERTY, Neo4JConfigProperties.NEO4J_URL);
    }

    Neo4JEstablishingConnectionFailedMessage withDataStore(DataStoreBase dataStore) {
        return with(ARG_DS_ID, dataStore.getId())  //
                .with(ARG_CONFIG_FILE, dataStore.getConfigFile());
    }

    Neo4JEstablishingConnectionFailedMessage withUrl(String url) {
        return with(ARG_URL, url);
    }

    Neo4JEstablishingConnectionFailedMessage withCommitPath(String commitPath) {
        return with(ARG_COMMIT_PATH, commitPath);
    }
}
