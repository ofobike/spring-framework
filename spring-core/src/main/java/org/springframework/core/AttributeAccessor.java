/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.core;

import org.springframework.lang.Nullable;


/**
 * 定义元数据访问的接口
 */
public interface AttributeAccessor {

	/**
	 * 设置属性
	 * @param name
	 * @param value
	 */
	void setAttribute(String name, @Nullable Object value);

	/**
	 * 获取这个属性
	 * @param name
	 * @return
	 */
	@Nullable
	Object getAttribute(String name);

	/**
	 * 移除这个属性
	 * @param name
	 * @return
	 */
	@Nullable
	Object removeAttribute(String name);

	/**
	 * 是否拥有这个属性
	 * @param name
	 * @return
	 */
	boolean hasAttribute(String name);

	/**
	 * 返回所有的属性
	 * @return
	 */
	String[] attributeNames();

}
