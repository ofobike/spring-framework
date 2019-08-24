
package org.springframework.beans.factory.config;

import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.AttributeAccessor;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

/**
 * A BeanDefinition describes a bean instance, which has property values,
 * constructor argument values, and further information supplied by
 * concrete implementations.
 *
 * <p>This is just a minimal interface: The main intention is to allow a
 * {@link BeanFactoryPostProcessor} to introspect and modify property values
 * and other bean metadata.
 *
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @since 19.03.2004
 * @see ConfigurableListableBeanFactory#getBeanDefinition
 * @see org.springframework.beans.factory.support.RootBeanDefinition
 * @see org.springframework.beans.factory.support.ChildBeanDefinition
 */
public interface BeanDefinition extends AttributeAccessor, BeanMetadataElement {


	/**
	 * 获取单例模式: singleton
	 */
	String SCOPE_SINGLETON = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

	/**
	 * 获取多例模式:prototype
	 */
	String SCOPE_PROTOTYPE = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;


	//Bean角色
	int ROLE_APPLICATION = 0;
	int ROLE_SUPPORT = 1;
	int ROLE_INFRASTRUCTURE = 2;


	//返回此bean定义的父bean定义的名称，如果有的话 <bean parent="">
	void setParentName(@Nullable String parentName);
	@Nullable
	String getParentName();

	//获取bean对象className <bean class="">
	void setBeanClassName(@Nullable String beanClassName);
	@Nullable
	String getBeanClassName();

	//<bean scope="singleton/prototype">
	void setScope(@Nullable String scope);
	@Nullable
	String getScope();

	//懒加载 <bean lazy-init="true/false">
	void setLazyInit(boolean lazyInit);
	boolean isLazyInit();

	//依赖对象  <bean depends-on="">
	void setDependsOn(@Nullable String... dependsOn);
	@Nullable
	String[] getDependsOn();

	//是否为被自动装配 <bean autowire-candidate="true/false">
	void setAutowireCandidate(boolean autowireCandidate);
	boolean isAutowireCandidate();

	//是否为主候选bean    使用注解：@Primary
	void setPrimary(boolean primary);
	boolean isPrimary();

	//定义创建该Bean对象的工厂l类  <bean factory-bean="">
	void setFactoryBeanName(@Nullable String factoryBeanName);
	@Nullable
	String getFactoryBeanName();

	//定义创建该Bean对象的工厂方法 <bean factory-method="">
	void setFactoryMethodName(@Nullable String factoryMethodName);
	@Nullable
	String getFactoryMethodName();

	//返回此bean的构造函数参数值
	ConstructorArgumentValues getConstructorArgumentValues();

	/**
	 * Return if there are constructor argument values defined for this bean.
	 * @since 5.0.2
	 */
	default boolean hasConstructorArgumentValues() {
		return !getConstructorArgumentValues().isEmpty();
	}

	//获取普通属性集合
	MutablePropertyValues getPropertyValues();
	default boolean hasPropertyValues() {
		return !getPropertyValues().isEmpty();
	}


	void setInitMethodName(@Nullable String initMethodName);

	@Nullable
	String getInitMethodName();

	/**
	 * Set the name of the destroy method.
	 * @since 5.1
	 */
	void setDestroyMethodName(@Nullable String destroyMethodName);

	/**
	 * Return the name of the destroy method.
	 * @since 5.1
	 */
	@Nullable
	String getDestroyMethodName();


	void setRole(int role);

	//获取这个bean的应用
	int getRole();

	/**
	 * Set a human-readable description of this bean definition.
	 * @since 5.1
	 */
	void setDescription(@Nullable String description);

	//返回对bean定义的可读描述。
	@Nullable
	String getDescription();


	// Read-only attributes

	/**
	 * Return a resolvable type for this bean definition,
	 * based on the bean class or other specific metadata.
	 * <p>This is typically fully resolved on a runtime-merged bean definition
	 * but not necessarily on a configuration-time definition instance.
	 * @return the resolvable type (potentially {@link ResolvableType#NONE})
	 * @since 5.2
	 * @see ConfigurableBeanFactory#getMergedBeanDefinition
	 */
	ResolvableType getResolvableType();

	//是否为单例
	boolean isSingleton();

	//是否为原型
	boolean isPrototype();

	//是否为抽象类
	boolean isAbstract();

	//返回该bean定义来自的资源的描述（用于在出现错误时显示上下文
	@Nullable
	String getResourceDescription();

	/**
	 * Return the originating BeanDefinition, or {@code null} if none.
	 * Allows for retrieving the decorated bean definition, if any.
	 * <p>Note that this method returns the immediate originator. Iterate through the
	 * originator chain to find the original BeanDefinition as defined by the user.
	 */
	@Nullable
	BeanDefinition getOriginatingBeanDefinition();

}
