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

package org.failearly.dataz.datastore.neo4j.internal.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.failearly.dataz.datastore.support.simplefile.SimpleFileStatement;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Neo4JStatements collects the Neo4J statements. Used by Jackson serializer.
 *
 * @see com.fasterxml.jackson.databind.ObjectMapper#writeValueAsString(Object)
 */
public final class Neo4JStatements {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static final Neo4JStatements NO_STATEMENTS = new Neo4JStatements();

    private final List<SimpleFileStatement> statements=new LinkedList<>();

    public Neo4JStatements() {
    }

    public void addStatement(SimpleFileStatement statement) {
        statements.add(statement);
    }

    /**
     * Used by Jackson.
     * @return a list of statement objects
     */
    @SuppressWarnings("unused")
    public List<Statement> getStatements() {
        return statements.stream().map(s -> new Statement(s.getContent())).collect(Collectors.toList());
    }

    public String toJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }

    public void reset() {
        statements.clear();
    }

    public static final class Statement {
        private final String statement;

        private Statement(String statement) {
            this.statement = statement;
        }

        /**
         * Used by Jackson.
         * @return a list of statement objects
         */
        @SuppressWarnings("unused")
        public String getStatement() {
            return statement;
        }
    }

}
