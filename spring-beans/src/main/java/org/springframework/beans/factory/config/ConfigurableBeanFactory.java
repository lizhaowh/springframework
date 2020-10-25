package org.springframework.beans.factory.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.lang.Nullable;

import java.beans.PropertyEditor;
import java.security.AccessControlContext;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";


    void setParentBeanFactory(BeanFactory parentBeanFactory) throws IllegalStateException;

    void setBeanClassLoader(@Nullable ClassLoader beanClassLoader);

    @Nullable
    ClassLoader getBeanClassLoader();

    void setTempClassLoader(@Nullable ClassLoader tempClassLoader);

    @Nullable
    ClassLoader getTempClassLoader();

    void setCacheBeanMetadata(boolean cacheBeanMetadata);

    boolean isCacheBeanMetadata();

    /**
     * 定义用于解析bean definition的表达式解析器
     * @param resolver
     */
    void setBeanExpressionResolver(@Nullable BeanExpressionResolver resolver);

    @Nullable
    BeanExpressionResolver getBeanExpressionResolver();

    /**
     * 类型转化器
     * @param conversionService
     */
    void setConversionService(@Nullable ConversionService conversionService);

    /**
     * Return the associated ConversionService, if any.
     * @since 3.0
     */
    @Nullable
    ConversionService getConversionService();

    /**
     * 属性编辑器
     *
     */
    void addPropertyEditorRegistrar(PropertyEditorRegistrar registrar);

    /**
     * Register the given custom property editor for all properties of the
     * given type. To be invoked during factory configuration.
     * <p>Note that this method will register a shared custom editor instance;
     * access to that instance will be synchronized for thread-safety. It is
     * generally preferable to use {@link #addPropertyEditorRegistrar} instead
     * of this method, to avoid for the need for synchronization on custom editors.
     * @param requiredType type of the property
     * @param propertyEditorClass the {@link PropertyEditor} class to register
     */
    void registerCustomEditor(Class<?> requiredType, Class<? extends PropertyEditor> propertyEditorClass);

    /**
     * Initialize the given PropertyEditorRegistry with the custom editors
     * that have been registered with this BeanFactory.
     * @param registry the PropertyEditorRegistry to initialize
     */
    void copyRegisteredEditorsTo(PropertyEditorRegistry registry);

    /**
     * BeanFactory用来转换bean属性值或者参数值的自定义转换器
     * @since 2.5
     * @see #addPropertyEditorRegistrar
     * @see #registerCustomEditor
     */
    void setTypeConverter(TypeConverter typeConverter);

    /**
     * Obtain a type converter as used by this BeanFactory. This may be a fresh
     * instance for each call, since TypeConverters are usually <i>not</i> thread-safe.
     * <p>If the default PropertyEditor mechanism is active, the returned
     * TypeConverter will be aware of all custom editors that have been registered.
     * @since 2.5
     */
    TypeConverter getTypeConverter();

    /**
     * Add a String resolver for embedded values such as annotation attributes.
     * @param valueResolver the String resolver to apply to embedded values
     * @since 3.0
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * Determine whether an embedded value resolver has been registered with this
     * bean factory, to be applied through {@link #resolveEmbeddedValue(String)}.
     * @since 4.3
     */
    boolean hasEmbeddedValueResolver();

    /**
     * Resolve the given embedded value, e.g. an annotation attribute.
     * @param value the value to resolve
     * @return the resolved value (may be the original value as-is)
     * @since 3.0
     */
    @Nullable
    String resolveEmbeddedValue(String value);

    /**
     * Add a new BeanPostProcessor that will get applied to beans created
     * by this factory. To be invoked during factory configuration.
     * <p>Note: Post-processors submitted here will be applied in the order of
     * registration; any ordering semantics expressed through implementing the
     * {@link org.springframework.core.Ordered} interface will be ignored. Note
     * that autodetected post-processors (e.g. as beans in an ApplicationContext)
     * will always be applied after programmatically registered ones.
     * @param beanPostProcessor the post-processor to register
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * Return the current number of registered BeanPostProcessors, if any.
     */
    int getBeanPostProcessorCount();

    /**
     * Register the given scope, backed by the given Scope implementation.
     * @param scopeName the scope identifier
     * @param scope the backing Scope implementation
     */
    void registerScope(String scopeName, Scope scope);

    /**
     * Return the names of all currently registered scopes.
     * <p>This will only return the names of explicitly registered scopes.
     * Built-in scopes such as "singleton" and "prototype" won't be exposed.
     * @return the array of scope names, or an empty array if none
     * @see #registerScope
     */
    String[] getRegisteredScopeNames();

    /**
     * Return the Scope implementation for the given scope name, if any.
     * <p>This will only return explicitly registered scopes.
     * Built-in scopes such as "singleton" and "prototype" won't be exposed.
     * @param scopeName the name of the scope
     * @return the registered Scope implementation, or {@code null} if none
     * @see #registerScope
     */
    @Nullable
    Scope getRegisteredScope(String scopeName);

    /**
     * Provides a security access control context relevant to this factory.
     * @return the applicable AccessControlContext (never {@code null})
     * @since 3.0
     */
    AccessControlContext getAccessControlContext();

    /**
     * Copy all relevant configuration from the given other factory.
     * <p>Should include all standard configuration settings as well as
     * BeanPostProcessors, Scopes, and factory-specific internal settings.
     * Should not include any metadata of actual bean definitions,
     * such as BeanDefinition objects and bean name aliases.
     * @param otherFactory the other BeanFactory to copy from
     */
    void copyConfigurationFrom(ConfigurableBeanFactory otherFactory);

    /**
     * Given a bean name, create an alias. We typically use this method to
     * support names that are illegal within XML ids (used for bean names).
     * <p>Typically invoked during factory configuration, but can also be
     * used for runtime registration of aliases. Therefore, a factory
     * implementation should synchronize alias access.
     * @param beanName the canonical name of the target bean
     * @param alias the alias to be registered for the bean
     * @throws BeanDefinitionStoreException if the alias is already in use
     */
    void registerAlias(String beanName, String alias) throws BeanDefinitionStoreException;

    /**
     * Resolve all alias target names and aliases registered in this
     * factory, applying the given StringValueResolver to them.
     * <p>The value resolver may for example resolve placeholders
     * in target bean names and even in alias names.
     * @param valueResolver the StringValueResolver to apply
     * @since 2.5
     */
    void resolveAliases(StringValueResolver valueResolver);

    /**
     * Return a merged BeanDefinition for the given bean name,
     * merging a child bean definition with its parent if necessary.
     * Considers bean definitions in ancestor factories as well.
     * @param beanName the name of the bean to retrieve the merged definition for
     * @return a (potentially merged) BeanDefinition for the given bean
     * @throws NoSuchBeanDefinitionException if there is no bean definition with the given name
     * @since 2.5
     */
    BeanDefinition getMergedBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

    /**
     * Determine whether the bean with the given name is a FactoryBean.
     * @param name the name of the bean to check
     * @return whether the bean is a FactoryBean
     * ({@code false} means the bean exists but is not a FactoryBean)
     * @throws NoSuchBeanDefinitionException if there is no bean with the given name
     * @since 2.5
     */
    boolean isFactoryBean(String name) throws NoSuchBeanDefinitionException;

    /**
     * Explicitly control the current in-creation status of the specified bean.
     * For container-internal use only.
     * @param beanName the name of the bean
     * @param inCreation whether the bean is currently in creation
     * @since 3.1
     */
    void setCurrentlyInCreation(String beanName, boolean inCreation);

    /**
     * Determine whether the specified bean is currently in creation.
     * @param beanName the name of the bean
     * @return whether the bean is currently in creation
     * @since 2.5
     */
    boolean isCurrentlyInCreation(String beanName);

    /**
     * Register a dependent bean for the given bean,
     * to be destroyed before the given bean is destroyed.
     * @param beanName the name of the bean
     * @param dependentBeanName the name of the dependent bean
     * @since 2.5
     */
    void registerDependentBean(String beanName, String dependentBeanName);

    /**
     * Return the names of all beans which depend on the specified bean, if any.
     * @param beanName the name of the bean
     * @return the array of dependent bean names, or an empty array if none
     * @since 2.5
     */
    String[] getDependentBeans(String beanName);

    /**
     * Return the names of all beans that the specified bean depends on, if any.
     * @param beanName the name of the bean
     * @return the array of names of beans which the bean depends on,
     * or an empty array if none
     * @since 2.5
     */
    String[] getDependenciesForBean(String beanName);

    /**
     * Destroy the given bean instance (usually a prototype instance
     * obtained from this factory) according to its bean definition.
     * <p>Any exception that arises during destruction should be caught
     * and logged instead of propagated to the caller of this method.
     * @param beanName the name of the bean definition
     * @param beanInstance the bean instance to destroy
     */
    void destroyBean(String beanName, Object beanInstance);

    /**
     * Destroy the specified scoped bean in the current target scope, if any.
     * <p>Any exception that arises during destruction should be caught
     * and logged instead of propagated to the caller of this method.
     * @param beanName the name of the scoped bean
     */
    void destroyScopedBean(String beanName);

    /**
     * Destroy all singleton beans in this factory, including inner beans that have
     * been registered as disposable. To be called on shutdown of a factory.
     * <p>Any exception that arises during destruction should be caught
     * and logged instead of propagated to the caller of this method.
     */
    void destroySingletons();
}
