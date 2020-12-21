package ldbc.snb.datagen.spark.transform

import frameless.TypedEncoder
import ldbc.snb.datagen.entities.dynamic.person.Person
import ldbc.snb.datagen.generator.generators.GenActivity
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.JavaTypeInference
import org.apache.spark.sql.catalyst.expressions.{BoundReference, Expression}
import org.apache.spark.sql.types.{DataType, ObjectType}
import org.spark_project.guava.reflect.TypeToken

import scala.reflect.ClassTag

object GraphAssembler {

//  trait Bean[T] {
//    def classTag: ClassTag[T]
//  }
//
//  def mkBean[T: ClassTag] = new Bean[T] {
//    override def classTag: ClassTag[T] = implicitly[ClassTag[T]]
//  }
//
//  implicit val beanForPerson: Bean[Person] = mkBean[Person]

//  object serializerFor {
//    private def serializerFor(inputObject: Expression, typeToken: TypeToken[_]): Expression = {
//      val ru = scala.reflect.runtime.universe
//      val mirror = ru.runtimeMirror(getClass.getClassLoader)
//      val moduleSymbol = ru.typeOf[JavaTypeInference.type].termSymbol.asModule
//      val moduleMirror = mirror.reflectModule(moduleSymbol)
//      val moduleInstance = moduleMirror.instance
//      val moduleInstanceClass = moduleInstance.getClass
//      val method = moduleInstanceClass.getDeclaredMethods.toList
//        .find(_.getName == "org$apache$spark$sql$catalyst$JavaTypeInference$$serializerFor")
//        .getOrElse(throw new RuntimeException("Unable to find JavaTypeInference.serializerFor(inputObject: Expression, typeToken: TypeToken[_])"))
//
////      val instanceMirror = mirror.reflect(moduleMirror.instance)
////
////      val tt = ru.typeOf[TypeToken[_]]
////      val serializerTerm = ru.typeOf[JavaTypeInference.type].decl(ru.TermName("serializerFor")).asTerm.alternatives.collectFirst {
////          case symbol if symbol.isMethod && symbol.asMethod.paramLists.headOption.exists {
////            case p1 :: p2 :: Nil
////              if p1.asTerm.info.erasure =:= ru.typeOf[Expression].erasure && p2.asTerm.info.erasure =:= tt.erasure => true
////            case _ => false
////          } => symbol.asMethod
////      }.getOrElse(throw new RuntimeException("Unable to find JavaTypeInference.serializerFor(inputObject: Expression, typeToken: TypeToken[_])"))
//
//      method.invoke(moduleInstance, inputObject, typeToken).asInstanceOf[Expression]
//    }
//
//    def apply(beanClass: Class[_]): Expression = {
//      val inputObject = BoundReference(0, ObjectType(beanClass), nullable = true)
//      serializerFor(inputObject, TypeToken.of(beanClass))
//    }
//  }

//  implicit def typedEncoderForBean[T: Bean] = {
//    implicit val classTag = implicitly[Bean[T]].classTag
//    val beanClass = classTag.runtimeClass
//    val (repr, _nullable) = JavaTypeInference.inferDataType(beanClass)
//    val deserializer = JavaTypeInference.deserializerFor(beanClass)
//    val serializer = serializerFor(beanClass)
//
//    new TypedEncoder[T] {
//      override def nullable: Boolean = _nullable
//      override def jvmRepr: DataType = ObjectType(beanClass)
//      override def catalystRepr: DataType = repr
//      override def fromCatalyst(path: Expression): Expression = deserializer
//      override def toCatalyst(path: Expression): Expression = serializer
//    }
//  }

  import frameless._

  def apply(persons: RDD[Person], activities: RDD[(Long, Array[GenActivity])])(implicit spark: SparkSession) = {
    import spark.implicits._

//    val df = spark.createDataset(persons)(TypedExpressionEncoder[Person])
//    df.printSchema()
//    df.show()

//    val df2 = spark.createDataset(activities)(null)
//    df2.printSchema()
//    df2.show()
  }

}
