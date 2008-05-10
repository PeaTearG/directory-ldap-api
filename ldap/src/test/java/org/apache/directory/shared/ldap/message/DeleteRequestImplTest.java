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
package org.apache.directory.shared.ldap.message;


import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

import javax.naming.InvalidNameException;
import javax.naming.ldap.Control;

import org.apache.directory.shared.ldap.message.AbandonListener;
import org.apache.directory.shared.ldap.message.DeleteRequest;
import org.apache.directory.shared.ldap.message.DeleteRequestImpl;
import org.apache.directory.shared.ldap.message.MessageException;
import org.apache.directory.shared.ldap.message.MessageTypeEnum;
import org.apache.directory.shared.ldap.message.ResultResponse;
import org.apache.directory.shared.ldap.name.LdapDN;


/**
 * TestCase for the methods of the DeleteRequestImpl class.
 * 
 * @author <a href="mailto:dev@directory.apache.org"> Apache Directory Project</a>
 * @version $Rev$
 */
public class DeleteRequestImplTest extends TestCase
{
	private static final Map<String, Control> EMPTY_CONTROL_MAP = new HashMap<String, Control>();

	/**
     * Tests the same object referrence for equality.
     */
    public void testEqualsSameObj()
    {
        DeleteRequestImpl req = new DeleteRequestImpl( 5 );
        assertTrue( req.equals( req ) );
    }


    /**
     * Tests for equality using exact copies.
     */
    public void testEqualsExactCopy() throws InvalidNameException
    {
        DeleteRequestImpl req0 = new DeleteRequestImpl( 5 );
        req0.setName( new LdapDN( "cn=admin,dc=example,dc=com" ) );

        DeleteRequestImpl req1 = new DeleteRequestImpl( 5 );
        req1.setName( new LdapDN( "cn=admin,dc=example,dc=com" ) );

        assertTrue( req0.equals( req1 ) );
    }


    /**
     * Test for inequality when only the IDs are different.
     */
    public void testNotEqualDiffId() throws InvalidNameException
    {
        DeleteRequestImpl req0 = new DeleteRequestImpl( 7 );
        req0.setName( new LdapDN( "cn=admin,dc=example,dc=com" ) );

        DeleteRequestImpl req1 = new DeleteRequestImpl( 5 );
        req1.setName( new LdapDN( "cn=admin,dc=example,dc=com" ) );

        assertFalse( req0.equals( req1 ) );
    }


    /**
     * Test for inequality when only the DN names are different.
     */
    public void testNotEqualDiffName() throws InvalidNameException
    {
        DeleteRequestImpl req0 = new DeleteRequestImpl( 5 );
        req0.setName( new LdapDN( "uid=akarasulu,dc=example,dc=com" ) );

        DeleteRequestImpl req1 = new DeleteRequestImpl( 5 );
        req1.setName( new LdapDN( "cn=admin,dc=example,dc=com" ) );

        assertFalse( req0.equals( req1 ) );
    }


    /**
     * Tests for equality even when another DeleteRequest implementation is
     * used.
     */
    public void testEqualsDiffImpl()
    {
        DeleteRequest req0 = new DeleteRequest()
        {
            public LdapDN getName()
            {
                return null;
            }


            public void setName( LdapDN name )
            {
            }


            public MessageTypeEnum getResponseType()
            {
                return MessageTypeEnum.DEL_RESPONSE;
            }


            public boolean hasResponse()
            {
                return true;
            }


            public MessageTypeEnum getType()
            {
                return MessageTypeEnum.DEL_REQUEST;
            }


            public Map<String, Control> getControls()
            {
                return EMPTY_CONTROL_MAP;
            }


            public void add( Control control ) throws MessageException
            {
            }


            public void remove( Control control ) throws MessageException
            {
            }


            public int getMessageId()
            {
                return 5;
            }


            public Object get( Object key )
            {
                return null;
            }


            public Object put( Object key, Object value )
            {
                return null;
            }


            public void abandon()
            {
            }


            public boolean isAbandoned()
            {
                return false;
            }


            public void addAbandonListener( AbandonListener listener )
            {
            }


            public ResultResponse getResultResponse()
            {
                return null;
            }


            public void addAll( Control[] controls ) throws MessageException
            {
            }
        };

        DeleteRequestImpl req1 = new DeleteRequestImpl( 5 );
        assertTrue( req1.equals( req0 ) );
    }
}
