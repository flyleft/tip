### 1. JPA注解
- **@Entity**: 声明为一个实体。**(修饰实体类)**
- **@Table**: 指定实体所映射的表。**(修饰实体类)**

  | 属性 | 是否必要 | 说明 |
  | :-------------- | :------------ | :------------ |
  | name    | 否 | 设置实体映射的表名。不指定则与实体类的类名相同 |
  | catalog | 否 | 设置实体映射的表放入指定的catalog中。不指定则放入默认的catalog中 |
  | schema  | 否 | 设置实体映射的表放入指定的schema中。 不指定则放入默认的schema中 |
  | uniqueConstraints| 否 | 为实体映射的表设置唯一的约束。该属性可以是一个@UniqueConstraint Annotation数组 |

- **@secondaryTable**: 把实体的部分属性映射到第二个数据表。可通过@secondaryTable指定多个额外的数据表。**(修饰实体类)**
   | 属性 | 是否必要 | 说明 |
   | :-------------- | :------------ | :------------ |
   | name       | 否 | 指定新数据表的表名 |
   | catalog | 否 | 设置实体映射的表放入指定的catalog中。不指定则放入默认的catalog中 |
   | schema  | 否 | 设置实体映射的表放入指定的schema中。 不指定则放入默认的schema中 |
   | uniqueConstraints| 否 | 为实体映射的表设置唯一的约束。 |
   | pkJoinColumns| 否 | 指定新数据表的一个或多个外键列，只有通过该外键列才可让新数据表中的记录参照到主表记录。该属性值是一个@PrimaryKeyJoinColumn数组 |

   **@PrimaryKeyJoinColumn**: 用于定义在从表中定义的外键列的映射信息。
   | 属性 | 是否必要 | 说明 |
   | :-------------- | :------------ | :------------ |
   | name                | 否 | 指定从表中的外键列的列名 |
   | columnDefinition    | 否 | 指定JPA使用该属性值指定的SQL片段来创建外键列 |
   | referencedColumnName| 否 | 指定从表中外键参照的数据列的列名 |
   ```
   @Entity
   @Table(name="person_table")
   @SecondaryTable(name="person_detail",pkJoinColumns=@PrimaryKeyJoinColumn(name="person_id"))
   public class Person{
   @Id
   private int id;
   @Column(name="person_name",length=50)
   private String name;
   @Column(table="person_detail",name="email")
   private String email;
   @Column(table="person_detail",name="phone")
   private String phone;
   }
   指定将实体状态放入第二个person_detail数据表中，并指定email,phone两个属性放入person_detail数据表中
   ```

- **@Column**: 指定属性映射的列信息，如列名，长度等。**(修饰属性)**

   | 属性 | 是否必要 | 说明 |
   | :-------------- | :------------ | :------------ |
   | name       | 否 | 指定该列的列名。默认为属性名 |
   | length     | 否 | 指定该列所能保存的数据的最大长度。默认为255 |
   | nullable   | 否 | 指定该列是否允许为null。默认为true |
   | unique     | 否 | 指定该列是否具有唯一约束。默认为false |
   | updatable  | 否 | 指定该列是否包含在JPA生成的update语句的列列表中。默认为true |
   | insertable | 否 | 指定该列是否包含在JPA生成的insert语句的列列表中。默认为true |

- **@Id**和**@GeneratedValue**：映射实体类的主键。**(修饰属性)**
@Id定义主键，可以是基本类型，基本类型的包装类，String，Date等类型。
@GeneratedValue：设置自动生成属性值，属性如下：
   | 属性 | 是否必要 | 说明 |
   | :-------------- | :------------ | :------------ |
   | strategy | 否 | 使用怎样的主键生成策略。GenerationType.AUTO：JPA自动选择最适合底层数据库的主键生成策略; GenerationType.IDENTITY: 对于mysql，sqlserver这样的数据库选择自增长的主键生成策略；GenerationType.SEQUENCE: 对于oracle这样的数据库，选择使用基于sequence的主键生成策略，应与@SequenceGenderator一起使用；GenerationType.TABLE：使用一个辅助表来生成主键，应与@TableGenderator一起使用|
   | generator| 否 | 当使用GenerationType.SEQUENCE,GenerationType.TABLE主键生成策略时，该属性指定sequence,辅助表的名称 |

- **@Transient**: 修饰不想持久保存的属性。**(修饰属性)**

- **@Enumerated**： 修饰枚举类型。**(修饰属性)**
```
当@Enumerated的value属性为EnumType.STRING时，底层数据库保存的是枚举值的名称；
当@Enumerated的value属性为EnumType.ORDINAL时，保存枚举值的序号。
如@Enumerated(EnumType.ORDINAL).
```

- **@Lob**:修饰大数据类型，对应JDBC的java.sql.Clob类型或者java.sql.Blob类型。**(修饰属性)**
```
当修饰的属性为byte[],Byte[],java.io.Serializable类型时，将映射为数据库底层的Blob列；
当修饰的属性为char[],Character[]或java.lang.String类型时，映射为底层的Clob列。
```

- **@Basic**：用于延迟加载操作。**(修饰属性)**
```
比如JPA加载Person实体时并不需要立即加载它的pic属性，而只加载一个"虚拟的"代理，真正需要pic属性再从数据库加载。
@Basic可以指定的属性：
fetch:指定是否需要延迟加载该属性。FetchType.EAGER不使用延迟加载，Fetch.LAZY使用延迟加载。
optional：指定映射的数据列是否允许使用null值。
例如：
@Lob
@Basic(fetch=FetchType.LAZY)
private byte[] pic;
```

- **@Temporal**: 修饰日期类型。**(修饰属性)**
```
@Temporal可以指定一个value属性，
该属性支持Temporal.DATE,Temporal.TIME,Temporal.TIMESTAMP，
分别对应于数据库date,time,timestamp类型的数据列。
```

- **@Embedded**和**@Embeddable**: 映射复合类型。@Embeadded修饰这个复合类型属性，@Embeaddable修饰这个复合类。
@AttributeOverride用来指定复合类型的成员属性的映射配置，它支持的属性：
   | 属性 | 是否必要 | 说明 |
   | :-------------- | :------------ | :------------ |
   | name       | 是 | 指定对复合类型中哪个属性进行配置 |
   | column     | 是 | 指定该属性所映射的数据列的列名   |
```
   @Entity
   @Table(name="person_table")
   public class Person{
   @Id
   private int id;
   @Column(name="person_name",length=50)
   private String name;
   @Embedded
   @AttributeOverrides({
    @AttributeOverride(name="name",column=@Column(name="cat_name",length=35)),
    @AttributeOverride(name="color",column=@Column("cat_color"))
   })
   private Cat cat;
   }
   @Embeddable
   public class Cat{
     private String name;
     private String color;
   }
```

- **@IdClass**和**@EmbeddedId**: 定义复合类型的主键。**(修饰属性)**
定义复合类型的主键有两种方式：
(1). 使用@IdClass和多个@Id;
(2). 使用一个@EmbeddedId即可。
```
方式一：使用@IdClass和多个@Id
   @Entity
   @Table(name="person_table")
   @IdClass(Cat.class)
   public class Person{
   //两个@Id定义联合主键
   @Id
   private int id;
   @Id
   private String name;
   private Cat cat;
   }
方式二：用一个@EmbeddedId
 @Entity
   @Table(name="person_table")
   public class Person{
   @Id
   private int id;
   @Column(name="person_name",length=50)
   private String name;
   @EmbeddedId
   @Embedded
   @AttributeOverrides({
    @AttributeOverride(name="name",column=@Column(name="cat_name",length=35)),
    @AttributeOverride(name="color",column=@Column("cat_color"))
   })
   private Cat cat;
   }
   @Embeddable
   public class Cat{
     private String name;
     private String color;
   }
```

- **@JoinColumn**: 定义外键。**(修饰属性)**
   | 属性 | 是否必要 | 说明 |
   | :-------------- | :------------ | :------------ |
   | columnDefinition | 否 | 指定JPA使用该属性值指定的SQL片段来创建外键列 |
   | name             | 否 | 指定该外键列的列名 |
   | insertable       | 否 | 指定该列是否包含在JPA生成的insert语句的列列表中。默认为true |
   | updatable        | 否 | 指定该列是否包含在JPA生成的update语句的列列表中。默认为true |
   | nullable         | 否 | 指定该列是否允许为null。默认为true |
   | table            | 否 | 指定该列所在的数据表的表名 |
   | unique           | 否 | 指定是否为该列增加唯一约束 |
   | referenceColumnName | 否| 指定该列所参照的主键列的列名 |

- **@ManyToOne**: 映射多对一关系。**(修饰属性)**
   | 属性 | 是否必要 | 说明 |
   | :-------------- | :------------ | :------------ |
   | cascade     | 否 | 指定JPA对关联实体采用怎样的级联策略，该级联策略支持四个属性值。CascadeType.ALL：指定JPA将所有的持久化操作都级联到关联实体；CascadeType.MERGE: 指定JPA将merge操作都级联到关联实体；CascadeType.PERSIST：指定JPA将persist操作级联到关联实体；CascadeType.REFRESH: 指定JPA将refresh操作级联到关联实体；CascadeType.REMOVE: 指定JPA将remove操作关联到关联实体|
   | fetch       | 否 | 指定抓取关联实体时抓取策略，该属性支持两个值。FetchType.EAGER: 抓取实体时，立即抓取关联实体，默认值；FetchType.LAZY：抓取实体时延迟抓取关联实体，等到真到用到时再去抓取。 |
   | optional    | 否 | 该属性指定关联关系是否可选。 |
   | targetEntity| 否 | 该属性指定关联实体的类名。 在默认情况下，JPA通过反射判断 |

- **@OneToOne**: 映射一对一关系。**(修饰属性)**
   | 属性 | 是否必要 | 说明 |
   | :-------------- | :------------ | :------------ |
   | cascade     | 否 | 指定JPA对关联实体采用怎样的级联策略，该级联策略支持四个属性值。CascadeType.ALL：指定JPA将所有的持久化操作都级联到关联实体；CascadeType.MERGE: 指定JPA将merge操作都级联到关联实体；CascadeType.PERSIST：指定JPA将persist操作级联到关联实体；CascadeType.REFRESH: 指定JPA将refresh操作级联到关联实体；CascadeType.REMOVE: 指定JPA将remove操作关联到关联实体|
   | fetch       | 否 | 指定抓取关联实体时抓取策略，该属性支持两个值。FetchType.EAGER: 抓取实体时，立即抓取关联实体，默认值；FetchType.LAZY：抓取实体时延迟抓取关联实体，等到真到用到时再去抓取。 |
   | optional    | 否 | 该属性指定关联关系是否可选。 |
   | targetEntity| 否 | 该属性指定关联实体的类名。 在默认情况下，JPA通过反射判断 |
   | mappedBy    | 否 | 该属性合法的属性值为关联实体的属性名，该属性指定关联实体中哪一个属性可引用到关联实体时采取抓取。 |

- **@OneToMany**：映射一对多关系。**(修饰属性)**
   | 属性 | 是否必要 | 说明 |
   | :-------------- | :------------ | :------------ |
   | cascade     | 否 | 指定JPA对关联实体采用怎样的级联策略，该级联策略支持四个属性值。CascadeType.ALL：指定JPA将所有的持久化操作都级联到关联实体；CascadeType.MERGE: 指定JPA将merge操作都级联到关联实体；CascadeType.PERSIST：指定JPA将persist操作级联到关联实体；CascadeType.REFRESH: 指定JPA将refresh操作级联到关联实体；CascadeType.REMOVE: 指定JPA将remove操作关联到关联实体|
   | fetch       | 否 | 指定抓取关联实体时抓取策略，该属性支持两个值。FetchType.EAGER: 抓取实体时，立即抓取关联实体，默认值；FetchType.LAZY：抓取实体时延迟抓取关联实体，等到真到用到时再去抓取。 |
   | targetEntity| 否 | 该属性指定关联实体的类名。 在默认情况下，JPA通过反射判断 |
   | mappedBy    | 否 | 该属性合法的属性值为关联实体的属性名，该属性指定关联实体中哪一个属性可引用到关联实体时采取抓取。 |

- **@ManyToMany**：映射多对多关系。**(修饰属性)**
   | 属性 | 是否必要 | 说明 |
   | :-------------- | :------------ | :------------ |
   | cascade     | 否 | 指定JPA对关联实体采用怎样的级联策略，该级联策略支持四个属性值。CascadeType.ALL：指定JPA将所有的持久化操作都级联到关联实体；CascadeType.MERGE: 指定JPA将merge操作都级联到关联实体；CascadeType.PERSIST：指定JPA将persist操作级联到关联实体；CascadeType.REFRESH: 指定JPA将refresh操作级联到关联实体；CascadeType.REMOVE: 指定JPA将remove操作关联到关联实体|
   | fetch       | 否 | 指定抓取关联实体时抓取策略，该属性支持两个值。FetchType.EAGER: 抓取实体时，立即抓取关联实体，默认值；FetchType.LAZY：抓取实体时延迟抓取关联实体，等到真到用到时再去抓取。 |
   | targetEntity| 否 | 该属性指定关联实体的类名。 在默认情况下，JPA通过反射判断 |
   | mappedBy    | 否 | 该属性合法的属性值为关联实体的属性名，该属性指定关联实体中哪一个属性可引用到关联实体时采取抓取。 |

- **@JoinTable**：专门用于多对多关联关系指定连接表的配置信息。
   | 属性 | 是否必要 | 说明 |
   | :-------------- | :------------ | :------------ |
   | name    | 否 | 指定该连接表的表名 |
   | catalog | 否 | 设置将该连接表放入指定的catalog内。如果没有指定该属性，连接表放入默认的catalog中。|
   | schema  | 否 | 设置将该连接表放入指定的schema内。 如果没有指定该属性，连接表放入默认的schema中。 |
   | joinColumns | 否 | 该属性值可接受多个@JoinColumn，用于配置连接表中外键列的列信息，这些列参照当前实体对应表的主键列 |
   | inverseJoinColumns | 否 |该属性值可接受多个@JoinColumn，用于配置连接表中外键列的列信息，这些列参照当前实体的关联实体对应表的主键列 |
   | uniqueConstraints  | 否 | 该属性为连接表增加唯一约束。 |

### 2. 关联

- **单向N-1关联**：使用**@ManyToOne**和**@JoinColumn**注解。
比如多个人对应同一个地址，只需从人实体端可以找到对应的地址实体，无须关心某一个地址的全部住户。
```
@Entity
@Table(name="address_table")
public class Address{
@Id
private int addressid;
@ManyToOne(optional=false,cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=Person.class)
@JoinColumn(name="person_id",nullable=false,updatable=false)//映射外键列
private Person person;
}
@Entity
@Table(name="person_table")
public class Person{
@Id
private int personid;
private String name;
}
```
