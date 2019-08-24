

package org.springframework.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

/**
 * Spring 源码学习之路开始(BeanFactory是Spring容器中的一个基本类也是很重要的一个类)
 */
public interface BeanFactory {

	//这里是对FactoryBean的转义定义，因为如果使用bean的名字检索FactoryBean得到的对象是工厂生成的对象，
	//如果需要得到工厂本身，需要转义
	//转义符“&”用来获取FactoryBean本身
	String FACTORY_BEAN_PREFIX = "&";


	//根据bean的名字进行获取bean的实例，这是IOC最大的抽象方法
	Object getBean(String name) throws BeansException;

	//根据bean的名字和Class类型进行获取Bean的实例,和上面方法不同的是，bean名字和Bean 的class类型不同时候会爆出异常
	<T> T getBean(String name, Class<T> requiredType) throws BeansException;
	Object getBean(String name, Object... args) throws BeansException;
    <T> T getBean(Class<T> requiredType) throws BeansException;
    <T> T getBean(Class<T> requiredType, Object... args) throws BeansException;
    <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType);
    <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType);

	//检测这个IOC容器中是否含有这个Bean
	boolean containsBean(String name);
	//判断这个Bean是不是单例
	boolean isSingleton(String name) throws NoSuchBeanDefinitionException;

	//检测Bena是不是多例(原型模式)
	boolean isPrototype(String name) throws NoSuchBeanDefinitionException;

	//查询指定的bean的名字和Class类型是不是指定的Class类型
	boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException;


	boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException;

	//这里对得到bean实例的Class类型
	@Nullable
	Class<?> getType(String name) throws NoSuchBeanDefinitionException;

	//这里得到bean的别名，如果根据别名检索，那么其原名也会被检索出来
	String[] getAliases(String name);

}
