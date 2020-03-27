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

import org.failearly.dataz.datastore.DataStoreException;
import org.failearly.dataz.datastore.neo4j.internal.json.Neo4JError;

import java.util.List;

/**
 * Neo4JDataStoreException is responsible for ...
 */
public class Neo4JDataStoreException extends DataStoreException {
    public Neo4JDataStoreException(List<Neo4JError> errors) {
        super("Neo4J Datastore complains about "+ errors.size() + " error(s):"+createErrorMessage(errors));
    }

    private static String createErrorMessage(List<Neo4JError> errors) {
        final StringBuilder builder=new StringBuilder();
        for (Neo4JError error : errors) {
            builder.append("\n\t").append(error).append("\n");
        }
        return builder.toString();
    }
}
