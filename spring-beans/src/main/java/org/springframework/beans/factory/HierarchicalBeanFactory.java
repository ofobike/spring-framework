/*
 * Copyright 2002-2012 the original author or authors.
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

package org.springframework.beans.factory;

import org.springframework.lang.Nullable;

/**
 * HierarchicalBeanFactory 提供父容器的访问功能.至于父容器的设置,
 * 需要找ConfigurableBeanFactory的setParentBeanFactory(接口把设置跟获取给拆开了!)
 */
public interface HierarchicalBeanFactory extends BeanFactory {

	//  返回本Bean工厂的父工厂
	@Nullable
	BeanFactory getParentBeanFactory();

	//  本地工厂(容器)是否包含这个Bean
	boolean containsLocalBean(String name);

}
