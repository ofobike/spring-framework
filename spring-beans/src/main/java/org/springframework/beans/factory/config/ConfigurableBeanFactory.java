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

package org.springframework.beans.factory.config;

import java.beans.PropertyEditor;
import java.security.AccessControlContext;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.Nullable;
import org.springframework.util.StringValueResolver;

/**
 * ConfigurableBeanFactory同时继承了HierarchicalBeanFactory 和 SingletonBeanRegistry 这两个接口，
 * 即同时继承了分层和单例类注册的功能
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

	//静态不可变常量分别代表单例类
	String SCOPE_SINGLETON = "singleton";

	//静态不可变常量分别代表原型类
	String SCOPE_PROTOTYPE = "prototype";


	//1个设置父工厂的方法，跟HierarchicalBeanFactory接口的getParentBeanFactory方法互补
	//搭配父接口HierarchicalBeanFactory 中得getParentBeanFactory()
	void setParentBeanFactory(BeanFactory parentBeanFactory) throws IllegalStateException;

	//设置bean的类加载器
	void setBeanClassLoader(@Nullable ClassLoader beanClassLoader);
	//返回类的加载器
	@Nullable
	ClassLoader getBeanClassLoader();
	//设置一个临时的类加载器
	void setTempClassLoader(@Nullable ClassLoader tempClassLoader);
	//返回一个临时的类加载器
	@Nullable
	ClassLoader getTempClassLoader();

	//设置是否缓存源，false石化，从新从类加载器加载(元数据)
	void setCacheBeanMetadata(boolean cacheBeanMetadata);
	//是否缓存了definitions
	boolean isCacheBeanMetadata();

	//bean表达式解析
	void setBeanExpressionResolver(@Nullable BeanExpressionResolver resolver);

	//获取Bean表达式解析
	@Nullable
	BeanExpressionResolver getBeanExpressionResolver();

	//设置一个转换服务
	void setConversionService(@Nullable ConversionService conversionService);

	//返回一个转换服务
	@Nullable
	ConversionService getConversionService();

	//设置属性注册
	void addPropertyEditorRegistrar(PropertyEditorRegistrar registrar);

	//属性注册编辑器
	void registerCustomEditor(Class<?> requiredType, Class<? extends PropertyEditor> propertyEditorClass);

	//复制属性编辑器
	void copyRegisteredEditorsTo(PropertyEditorRegistry registry);

	//设置类型转换器
	void setTypeConverter(TypeConverter typeConverter);

	//获取类型转换器
	TypeConverter getTypeConverter();

	// 增加一个嵌入式的StringValueResolver
	void addEmbeddedValueResolver(StringValueResolver valueResolver);

	//是否有转换器
	boolean hasEmbeddedValueResolver();


	@Nullable
	String resolveEmbeddedValue(String value);

	//增加一个BeanPostProcessor的处理器 前后
	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

	//获取前置处理器的数量
	int getBeanPostProcessorCount();

	//注册bean的范围
	void registerScope(String scopeName, Scope scope);

	//获取Bean的注册范围
	String[] getRegisteredScopeNames();

	//获取Bean范围
	@Nullable
	Scope getRegisteredScope(String scopeName);

	//返回本工厂的一个安全访问的上下文
	AccessControlContext getAccessControlContext();

	//复制其他工厂的属性配置
	void copyConfigurationFrom(ConfigurableBeanFactory otherFactory);

	//注册bean起一个别名
	void registerAlias(String beanName, String alias) throws BeanDefinitionStoreException;

	//根据StringVlauesReslvers 来移除bean
	void resolveAliases(StringValueResolver valueResolver);

	//返回合并后的BeanDefinition
	BeanDefinition getMergedBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

	//返回一个Bean是否是工厂
	boolean isFactoryBean(String name) throws NoSuchBeanDefinitionException;

	//设置一个是否正在创建bean
	void setCurrentlyInCreation(String beanName, boolean inCreation);

	//指定bean是否创建成功
	boolean isCurrentlyInCreation(String beanName);

	//注册这个Bean的依赖
	void registerDependentBean(String beanName, String dependentBeanName);

	//获取指定bean
	String[] getDependentBeans(String beanName);

	/**
	 * Return the names of all beans that the specified bean depends on, if any.
	 * @param beanName the name of the bean
	 * @return the array of names of beans which the bean depends on,
	 * or an empty array if none
	 * @since 2.5
	 */
	String[] getDependenciesForBean(String beanName);

	//销毁Bean
	void destroyBean(String beanName, Object beanInstance);

	//销毁指定返回的Bean
	void destroyScopedBean(String beanName);

	//销毁单例Bean
	void destroySingletons();

}
