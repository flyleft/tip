1. 分词器分词分类:
   - num:数字,形如23.6,56
   - char:字符,形如'A',并对一些特殊字符进行转义
   - id:标识符和符号:形如token,&&,!=
2. 分词各个分类的正则(scala中"""括起来的会自动转义),其中字符串和字符还包括转义字符
   - num或者字符串: val numStr="""((//.*)|([0-9]+)|("([^"]|\\n|\\"|\\\\)*")|"""
   - char: val char="""('([^']|\\n|\\r|\\t|\\"|\\)')|"""
   - 标识符或者符号:val id="""([A-Z_a-z][A-Z_a-z0-9]*|\+=|-=|=>|:=|<-|\.\.|\*=|\\=|&&|==|\?\.|<=|>=|!=|\p{Punct}|\|\|))?"""
   - 将三者组装成正则表达式:val reg=(numStr+char+id).r
3. 从输入流逐行解析匹配(in为传入的输入流)
```scala
protected def read(): Unit = {
    for (line <- Source.fromInputStream(in).getLines()) {
      lineNumber=lineNumber+1
      parseLine(line,lineNumber)
    }
  }
```
4. 匹配正则加入到对应的Token中
```scala
private def parseLine(line: String,lineNumber:Int): Unit = {
    val matchIter: MatchIterator = reg.findAllIn(line)
    while (matchIter.hasNext) {
      val reg(none1, none2, num, str, none3,char,none4, id) = matchIter.next()
      if (num != null) {
        println("num:" + num)
        new NumToken(lineNumber,num.toInt)::queue
      } else if (str != null) {
        println("str:" + str)
        new StrToken(lineNumber,str)::queue
      } else if(char!=null){
        println("char:" + char)
        new CharToken(lineNumber,char)
      } else if (id != null) {
        println("id:" + id)
        new IdToken(lineNumber,id)::queue
      }
    }
  }
```
5.对应的Toke

```scala
abstract class Token(line: Int) {

  def isIdentifier: Boolean = {
    return false
  }
  def getLine:Int={
    line
  }
  def isNumber: Boolean = {
    return false
  }

  def isString: Boolean = {
    return false
  }

  def isChar: Boolean = {
    return false
  }

  def getNumber: Int = {
    throw new RuntimeException("not number token")
  }

  def getText: String = {
    return ""
  }

  def getChar: Char = {
    throw new RuntimeException("not char token")
  }
}

object Token {
  val EOF: Token = new Token((-1)) {}
  val EOL = "\\n"
}

protected class NumToken(line: Int, value: Int) extends Token(line: Int) {
  override def isNumber: Boolean = {
    return true
  }

  override def getText: String = {
    return Integer.toString(value)
  }

  override def getNumber: Int = {
    return value
  }

  override def toString: String = super.toString
}

protected class IdToken(line: Int, id: String) extends Token(line: Int) {
  override def isIdentifier: Boolean = {
    return true
  }

  override def getText: String = {
    return id
  }
}

protected class StrToken(line: Int, str: String) extends Token(line: Int) {
  override def isString: Boolean = {
    return true
  }

  override def getText: String = {
    return str
  }
}

protected class CharToken(line: Int, char: String) extends Token(line: Int) {
  override def isChar: Boolean = true

  override def getChar: Char = {
    val array = char.toCharArray
    if (array == null || array.length != 1) {
      throw new RuntimeException("not char token")
    } else {
      return array(0)
    }
  }
}
```

## 完整的Lexer代码

```scala
class Lexer(in: InputStream) {
  val numStr="""((//.*)|([0-9]+)|("([^"]|\\n|\\"|\\\\)*")|"""
  val char="""('([^']|\\n|\\r|\\t|\\"|\\)')|"""
  val id="""([A-Z_a-z][A-Z_a-z0-9]*|\+=|-=|=>|:=|<-|\.\.|\*=|\\=|&&|==|\?\.|<=|>=|!=|\p{Punct}|\|\|))?"""
  val reg=(numStr+char+id).r
  val queue: List[Token] = List()
  private var lineNumber=0
  protected def read(): Unit = {
    for (line <- Source.fromInputStream(in).getLines()) {
      lineNumber=lineNumber+1
      parseLine(line,lineNumber)
    }
  }
  def getAllTokens: List[Token] ={
    read()
    queue
  }
  private def parseLine(line: String,lineNumber:Int): Unit = {
    val matchIter: MatchIterator = reg.findAllIn(line)
    while (matchIter.hasNext) {
      val reg(none1, none2, num, str, none3,char,none4, id) = matchIter.next()
      if (num != null) {
        println("num:" + num)
        new NumToken(lineNumber,num.toInt)::queue
      } else if (str != null) {
        println("str:" + str)
        new StrToken(lineNumber,str)::queue
      } else if(char!=null){
        println("char:" + char)
        new CharToken(lineNumber,char)
      } else if (id != null) {
        println("id:" + id)
        new IdToken(lineNumber,id)::queue
      }
    }
  }
}
```
##测试类代码
```scala
object LexerTest {
  def main(args: Array[String]) {
    val in=new FileInputStream("zuyi/struct.zy")
    new Lexer(in)
  }
}
```
## struct.zy代码文件,用于测试

```
pkg zuyi

struct student(
   name    String
   age     Int
   scores  (
   math    Float
   english Float
   )
)
fn (student stu) studentToStr:String
   """
          {
            "name":$name,
            "age":$age,
            "scores":[
             "math":$math,
             "english":$english
            ]
          }
   """.filer(_==Char.SPACE)
```

##测试结果
```
id:pkg
id:zuyi
id:struct
id:student
id:(
id:name
id:String
id:age
id:Int
id:scores
id:(
id:math
id:Float
id:english
id:Float
id:)
id:)
id:fn
id:(
id:student
id:stu
id:)
id:studentToStr
id::
id:String
str:""
id:"
id:{
str:"name"
id::
id:$
id:name
id:,
str:"age"
id::
id:$
id:age
id:,
str:"scores"
id::
id:[
str:"math"
id::
id:$
id:math
id:,
str:"english"
id::
id:$
id:english
id:]
id:}
str:""
id:"
id:.
id:filer
id:(
id:_
id:==
id:Char
id:.
id:SPACE
id:)
id:#
```