#jpa

####jpa是Java Persistence API简称

####主键生成策略GenerationType

> - TABLE 使用一个特定的数据库表格来保存主键，这个策略可以由表模拟序列产生主键， 不依赖于数据库的具体实现，有利于数据库的移植。序列表有一个序列名和对应值两个字段，只需要一张序列就可以用于多张表的主键生成
> - SEQUENCE 根据底层数据库的序列来生成主键，条件是数据库支持序列，通过序列产生主键,Oracle不支持ID子增长列,而是使用序列的机制生成主键ID,所以SEQUENCE适用于Oracle，Mysql不支持这种策略
> - IDENTITY 主键由数据库自动生成（主要是自动增长型)
> - AUTO 主键由程序控制  使用 AUTO 策略就是将主键生成的策略交给持久化引擎 (persistence engine) 来决定，由它自己选择从 Table 策略，Sequence 策略和 Identity 策略三种策略中选择合适的主键生成策略。

#### JpaRepository

> 实际上JpaRepository实现了PagingAndSortingRepository接口，PagingAndSortingRepository接口实现了CrudRepository接口，CrudRepository接口实现了Repository接口(下文会细说)

#### 使用方法

> ```java
> public interface DemoBasicDao extends JpaRepository<Demo, Integer> {
>     /**
>      * where name=${name} and age=${age}
>      *
>      * @param name
>      * @param age
>      * @return
>      */
>     Demo findByNameAndAge(String name, int age);
>
>     /**
>      * where id in(${ids})
>      *
>      * @param ids
>      * @return
>      */
>     List<Demo> queryByIdIn(List<Integer> ids);
>
>     /**
>      * where id between ${var1} and ${var2}
>      *
>      * @param var1
>      * @param var2
>      * @return
>      */
>     List<Demo> getByIdBetween(int var1, int var2);
>
>     /**
>      * where id<${id}
>      *
>      * @param id
>      * @return
>      */
>     List<Demo> queryByIdLessThan(int id);
>
>     /**
>      * where id>${id}
>      *
>      * @param id
>      * @return
>      */
>     List<Demo> queryByIdGreaterThan(int id);
>
>     /**
>      * where id<=${id}
>      *
>      * @param id
>      * @return
>      */
>     List<Demo> queryByIdLessThanEqual(int id);
>
>     /**
>      * where id>=${id}
>      *
>      * @param id
>      * @return
>      */
>     List<Demo> queryByIdGreaterThanEqual(int id);
>
>     /**
>      * where name like ${name}
>      *
>      * @param name
>      * @return
>      */
>     List<Demo> queryByNameLike(String name);
>
>     /**
>      * where name like %name
>      *
>      * @param name
>      * @return
>      */
>     List<Demo> queryByNameStartingWith(String name);
>
>     /**
>      * where name like name%
>      *
>      * @param name
>      * @return
>      */
>     List<Demo> queryByNameEndingWith(String name);
>
>     /**
>      * order by id desc
>      *
>      * @param ids
>      * @return
>      */
>     List<Demo> queryByIdInOrderByIdDesc(List<Integer> ids);
>
> }
> ```
>
> ```Java
> public interface DemoHighDao extends JpaRepository<Demo,Integer> {
>     @Query("select demo from Demo demo where demo.id=:id")
>     Demo aaaaa(@Param("id") int id);
>
>     List<Demo> queryByIdIn(List<Integer> id, Sort sort);
>
>     List<Demo> queryByIdIn(List<Integer> id, Pageable pageable);
>
>     DemoMap queryById(int id);
> }
> ```
>
> 

####关键注解

> ```
> spring.datasource.platform=h2
> spring.datasource.url=jdbc:h2:mem:demo;MODE=MYSQL
> spring.datasource.driver-class-name=org.h2.Driver
> spring.datasource.username=root
> spring.datasource.password=
>
> spring.h2.console.enabled=true
> spring.h2.console.path=/db
> spring.datasource.schema=classpath:db/schema.sql
> spring.datasource.data=classpath:db/data.sql
> spring.jpa.hibernate.ddl-auto=none
> ```
>
> 注意：__spring.jpa.hibernate.ddl-auto__
>
> > - validate：加载hibernate时，验证创建数据库表结构
> > - create：每次加载hibernate，重新创建数据库表结构，这就是导致数据库表数据丢失的原因。
> > - create-drop：加载hibernate时创建，退出是删除表结构
> > - update：加载hibernate自动更新数据库结构

####代码github地址：

> __git@github.com:Mr-ChangK/jpademo.git__

#### 原理详解

> - RepositoryFactoryBeanSupport：为spring的FactoryBean接口开发的适配器，可以很便捷的通过spring配置设置repository工厂
>
> - RepositoryFactorySupport：创建一个给定repository接口实例的工厂bean。创建一个实现配置的repository接口的代理，并应用一个advice将控制交给QueryExecuterMethodInterceptor
>
> - RepositoryQuery
>
>   > - SimpleJpaQuery：方法头上@Query注解的nativeQuery属性缺省值为false，也就是使用JPQL，此时会创建SimpleJpaQuery实例，并通过两个StringQuery类实例分别持有query jpql语句和根据query jpql计算拼接出来的countQuery jpql语句； 
>   > - NativeJpaQuery：方法头上@Query注解的nativeQuery属性如果显式的设置为nativeQuery=true，也就是使用原生SQL，此时就会创建NativeJpaQuery实例； 
>   > - PartTreeJpaQuery：方法头上未进行@Query注解，将使用spring-data-jpa独创的方法名识别的方式进行sql语句拼接，此时在spring-data-jpa内部就会创建一个PartTreeJpaQuery实例； 
>   > - NamedQuery ：使用javax.persistence.NamedQuery注解访问数据库的形式，此时在spring-data-jpa内部就会根据此注解选择创建一个NamedQuery实例； 
>   > - StoredProcedureJpaQuery ：顾名思义，在Repository接口的方法头上使用org.springframework.data.jpa.repository.query.Procedure注解，也就是调用存储过程的方式访问数据库，此时在spring-data-jpa内部就会根据@Procedure注解而选择创建一个StoredProcedureJpaQuery实例。 
>
> - QueryMethod
>
> - JpaQueryExecution

每一个方法都创建了一个

private final Map<Method, RepositoryQuery> queries = new ConcurrentHashMap<Method, RepositoryQuery>();  

QueryLookupStrategy会决定使用哪个

PartTree建立查询结构



RepositoryQuery三种实例

#### sql解析原理详解

> - ##### 主要的类
>
>   > - RepositoryFactoryBeanSupport：为spring的FactoryBean接口开发的适配器，可以很便捷的通过spring配置设置repository工厂
>   >
>   > - RepositoryFactorySupport：创建一个给定repository接口实例的工厂bean。创建一个实现配置的repository接口的代理，并应用一个advice将控制交给QueryExecuterMethodInterceptor
>   >
>   > - QueryLookupStrategy：是一个枚举类，有三种枚举，返回QueryLookupStrategy的实例，主要用来返回一个RepositoryQuery类
>   >
>   > - RepositoryQuery：
>   >
>   >   > - SimpleJpaQuery：方法头上@Query注解的nativeQuery属性缺省值为false，也就是使用JPQL，此时会创建SimpleJpaQuery实例，并通过两个StringQuery类实例分别持有query jpql语句和根据query jpql计算拼接出来的countQuery jpql语句； 
>   >   > - NativeJpaQuery：方法头上@Query注解的nativeQuery属性如果显式的设置为nativeQuery=true，也就是使用原生SQL，此时就会创建NativeJpaQuery实例； 
>   >   > - PartTreeJpaQuery：方法头上未进行@Query注解，将使用spring-data-jpa独创的方法名识别的方式进行sql语句拼接，此时在spring-data-jpa内部就会创建一个PartTreeJpaQuery实例； 
>   >   > - NamedQuery ：使用javax.persistence.NamedQuery注解访问数据库的形式，此时在spring-data-jpa内部就会根据此注解选择创建一个NamedQuery实例； 
>   >   > - StoredProcedureJpaQuery ：顾名思义，在Repository接口的方法头上使用org.springframework.data.jpa.repository.query.Procedure注解，也就是调用存储过程的方式访问数据库，此时在spring-data-jpa内部就会根据@Procedure注解而选择创建一个StoredProcedureJpaQuery实例。
>   >
>   > - PartTree：用于从方法中解析出sql语法
>
> - ##### 过程
>
>   > RepositoryFactoryBeanSupport实现了InitializingBean接口，所以在bean初始化中直接执行afterPropertiesSet方法，这个方法会初始化RepositoryFactorySupport的实例，这个实例的getRepository方法会生成一个代理工厂，这个代理工厂会加入一个拦截器QueryExecutorMethodInterceptor，这个拦截器中对于每个方法都会有一个private final Map<Method, RepositoryQuery> queries = new ConcurrentHashMap<Method, RepositoryQuery>();  实例主要是调用QueryLookupStrategy中的resolveQuery来解析接口方法返回一个RepositoryQuery的实例，比如PartTreeJpaQuery这个中有一个PartTree变量，通过这个变量，PartTree通过正则表达式来风格变量来得出sql的语法结构

