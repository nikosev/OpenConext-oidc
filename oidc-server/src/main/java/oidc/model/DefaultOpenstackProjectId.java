/*******************************************************************************
 * Copyright 2016 The MITRE Corporation
 *   and the MIT Internet Trust Consortium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package oidc.model;

import oidc.model.OpenstackProjectId;

/**
 * @author  nikosev
 */
public class DefaultOpenstackProjectId implements OpenstackProjectId {

	private static final long serialVersionUID = -3324293402500952899L;
	private String openstackProjectId;
	private String scopeName;

	/**
	 * Empty constructor
	 */
	public DefaultOpenstackProjectId() {

	}

	/**
	 * Copy constructor from an existing address.
	 * @param address
	 */
	public DefaultOpenstackProjectId(OpenstackProjectId openstackProjectId) {
		setOpenstackProjectId(openstackProjectId.getOpenstackProjectId());
		setScopeName(openstackProjectId.getScopeName());
	}

	/**
	 * @return the openstackProjectId
	 */
	public String getOpenstackProjectId() {
		return openstackProjectId;
	}

	/**
	 * @param openstackProjectId the openstackProjectId to set
	 */
	public void setOpenstackProjectId(String openstackProjectId) {
		this.openstackProjectId = openstackProjectId;
	}

	/**
	 * @return the scopeName
	 */
	public String getScopeName() {
		return scopeName;
	}

	/**
	 * @param scopeName the scopeName to set
	 */
	public void setScopeName(String scopeName) {
		this.scopeName = scopeName;
	}

	
}