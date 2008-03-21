/*
 * Copyright 2004-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.webflow.engine.model;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * Model support for var elements.
 * <p>
 * An instance variable. Variables are created when the flow starts or state enters and destroyed when the flow or state
 * ends, respectively.
 * 
 * @author Scott Andrews
 */
public class VarModel extends AbstractModel {
	private String name;
	private String className;
	private String scope;

	/**
	 * Create a variable model
	 * @param name the name of the variable
	 * @param className the class type of the variable
	 */
	public VarModel(String name, String className) {
		setName(name);
		setClassName(className);
	}

	/**
	 * Create a variable model
	 * @param name the name of the variable
	 * @param className the class type of the variable
	 * @param scope the scope to store the variable
	 */
	public VarModel(String name, String className, String scope) {
		setName(name);
		setClassName(className);
		setScope(scope);
	}

	/**
	 * Merge properties
	 * @param model the var to merge into this var
	 */
	public void merge(Model model) {
		if (isMergeableWith(model)) {
			VarModel var = (VarModel) model;
			setClassName(merge(getClassName(), var.getClassName()));
			setScope(merge(getScope(), var.getScope()));
		}
	}

	/**
	 * Tests if the model is able to be merged with this var
	 * @param model the model to test
	 */
	public boolean isMergeableWith(Model model) {
		if (model == null) {
			return false;
		}
		if (!(model instanceof VarModel)) {
			return false;
		}
		VarModel var = (VarModel) model;
		return ObjectUtils.nullSafeEquals(getName(), var.getName());
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (!(obj instanceof VarModel)) {
			return false;
		}
		VarModel var = (VarModel) obj;
		if (var == null) {
			return false;
		} else if (!ObjectUtils.nullSafeEquals(getName(), var.getName())) {
			return false;
		} else if (!ObjectUtils.nullSafeEquals(getClassName(), var.getClassName())) {
			return false;
		} else if (!ObjectUtils.nullSafeEquals(getScope(), var.getScope())) {
			return false;
		} else {
			return true;
		}
	}

	public int hashCode() {
		return ObjectUtils.nullSafeHashCode(getName()) * 27 + ObjectUtils.nullSafeHashCode(getClassName()) * 27
				+ ObjectUtils.nullSafeHashCode(getScope()) * 27;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		if (StringUtils.hasText(name)) {
			this.name = name;
		} else {
			this.name = null;
		}
	}

	/**
	 * @return the class name
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the class name to set
	 */
	public void setClassName(String className) {
		if (StringUtils.hasText(className)) {
			this.className = className;
		} else {
			this.className = null;
		}
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		if (StringUtils.hasText(scope)) {
			this.scope = scope;
		} else {
			this.scope = null;
		}
	}
}