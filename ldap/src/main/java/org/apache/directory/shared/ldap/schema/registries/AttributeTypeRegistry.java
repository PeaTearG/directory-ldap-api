/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */
package org.apache.directory.shared.ldap.schema.registries;


import org.apache.directory.shared.ldap.schema.AttributeType;
import org.apache.directory.shared.ldap.schema.Normalizer;
import org.apache.directory.shared.ldap.schema.SchemaObjectType;
import org.apache.directory.shared.ldap.schema.normalizers.OidNormalizer;
import org.apache.directory.shared.ldap.schema.parsers.AttributeTypeDescription;
import org.apache.directory.shared.ldap.schema.parsers.NormalizerDescription;

import javax.naming.NamingException;
import java.util.Iterator;
import java.util.Map;


/**
 * An AttributeType registry service interface.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 * @version $Rev$
 */
public class AttributeTypeRegistry extends SchemaObjectRegistry<AttributeType>
{
    /**
     * Creates a new default AttributeTypeRegistry instance.
     */
    public AttributeTypeRegistry()
    {
        super( SchemaObjectType.ATTRIBUTE_TYPE );
    }
    
    
    /**
     * Gets an oid/name to normalizer mapping used to normalize distinguished 
     * names.
     *
     * @return a map of OID Strings to OidNormalizer instances
     * @throws NamingException if for some reason this information cannot be returned
     */
    Map<String, OidNormalizer> getNormalizerMapping() throws NamingException; 
    
    /**
     * Quick lookup to see if an attribute has descendants.
     * 
     * @param ancestorId the name alias or OID for an attributeType
     * @return an Iterator over the AttributeTypes which have the ancestor
     * within their superior chain to the top
     * @throws NamingException if the ancestor attributeType cannot be 
     * discerned from the ancestorId supplied
     */
    boolean hasDescendants( String ancestorId ) throws NamingException;
    
    /**
     * Get's an iterator over the set of descendant attributeTypes for
     * some ancestor's name alias or their OID.
     * 
     * @param ancestorId the name alias or OID for an attributeType
     * @return an Iterator over the AttributeTypes which have the ancestor
     * within their superior chain to the top
     * @throws NamingException if the ancestor attributeType cannot be 
     * discerned from the ancestorId supplied
     */
    Iterator<AttributeType> descendants( String ancestorId ) throws NamingException;
}
