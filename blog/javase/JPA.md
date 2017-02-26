### 1. 注解
- **@Entity**: 声明为一个实体。(修饰实体类)
- **@Table**: 指定实体所映射的表。(修饰实体类)支持的属性如下表

  | 属性 | 是否必要 | 说明 |
  | :-------------- | :------------ | :------------ |
  | catalog | 否 | 设置实体映射的表放入指定的catalog中。不指定则放入默认的catalog中 |
  | name    | 否 | 设置实体映射的表名。不指定则与实体类的类名相同 |
  | schema  | 否 | 设置实体映射的表放入指定的schema中。 不指定则放入默认的schema中 |
  | uniqueConstraints| 否 | 为实体映射的表设置唯一的约束。该属性可以是一个@UniqueConstraint Annotation数组 |

- **@Column**: 指定属性映射的列信息，如列名，长度等。(修饰属性)

   | 属性 | 是否必要 | 说明 |
   | :-------------- | :------------ | :------------ |
   | name       | 否 | 指定该列的列名。默认为属性名 |
   | length     | 否 | 指定该列所能保存的数据的最大长度。默认为255 |
   | nullable   | 否 | 指定该列是否允许为null。默认为true |
   | unique     | 否 | 指定该列是否具有唯一约束。默认为false |
   | updatable  | 否 | 指定该列是否包含在JPA生成的update语句的列列表中。默认为true |
   | insertable | 否 | 指定该列是否包含在JPA生成的insert语句的列列表中。默认为true |

- **@Transient**: 修饰不想持久保存的属性。(修饰属性)

- @Enumerated： 修饰枚举类型。(修饰属性)
  > 当@Enumerated的value属性为EnumType.STRING时，底层数据库保存的是枚举值的名称；当@Enumerated的value属性为EnumType.ORDINAL时，保存枚举值的序号。如@Enumerated(EnumType.ORDINAL).

