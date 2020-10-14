#include <IntMatrix__ValueIterator.h>


#include "IntMatrix__ValueIterator.inc"

x10::lang::Iterator<x10_int>::itable<IntMatrix__ValueIterator >  IntMatrix__ValueIterator::_itable_0(&IntMatrix__ValueIterator::equals, &IntMatrix__ValueIterator::hasNext, &IntMatrix__ValueIterator::hashCode, &IntMatrix__ValueIterator::next, &IntMatrix__ValueIterator::toString, &IntMatrix__ValueIterator::typeName);
x10::lang::Any::itable<IntMatrix__ValueIterator >  IntMatrix__ValueIterator::_itable_1(&IntMatrix__ValueIterator::equals, &IntMatrix__ValueIterator::hashCode, &IntMatrix__ValueIterator::toString, &IntMatrix__ValueIterator::typeName);
x10aux::itable_entry IntMatrix__ValueIterator::_itables[3] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Iterator<x10_int> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<IntMatrix__ValueIterator>())};
void IntMatrix__ValueIterator::_instance_init() {
    _I_("Doing initialisation for class: IntMatrix__ValueIterator");
    
}


//#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": x10.ast.X10FieldDecl_c

//#line 516 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": x10.ast.X10FieldDecl_c

//#line 518 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": x10.ast.X10MethodDecl_c
x10_boolean IntMatrix__ValueIterator::hasNext() {
    
    //#line 519 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": x10.ast.X10Return_c
    return (!x10aux::struct_equals(((x10aux::ref<IntMatrix__ValueIterator>)this)->
                                     FMGL(index), x10aux::nullCheck(((x10aux::ref<IntMatrix__ValueIterator>)this)->
                                                                      FMGL(out__))->
                                                    FMGL(m)));
    
}

//#line 521 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": x10.ast.X10MethodDecl_c
x10_int IntMatrix__ValueIterator::next() {
    
    //#line 522 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int value = x10aux::nullCheck(x10aux::nullCheck(((x10aux::ref<IntMatrix__ValueIterator>)this)->
                                                          FMGL(out__))->
                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                      ((x10_int)0),
                      ((x10aux::ref<IntMatrix__ValueIterator>)this)->
                        FMGL(index));
    
    //#line 523 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<IntMatrix__ValueIterator>)this)->
      FMGL(index) = ((x10_int) ((((x10aux::ref<IntMatrix__ValueIterator>)this)->
                                   FMGL(index)) + (((x10_int)1))));
    
    //#line 524 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": x10.ast.X10Return_c
    return value;
    
}

//#line 515 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": x10.ast.X10ConstructorDecl_c
void IntMatrix__ValueIterator::_constructor(
  x10aux::ref<IntMatrix> out__) {
    
    //#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<IntMatrix__ValueIterator>)this)->
      FMGL(out__) = out__;
    this->x10::lang::Object::_constructor();
    
    //#line 515 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<IntMatrix__ValueIterator>)this)->IntMatrix__ValueIterator::__fieldInitializers117();
    
}
x10aux::ref<IntMatrix__ValueIterator> IntMatrix__ValueIterator::_make(
  x10aux::ref<IntMatrix> out__) {
    x10aux::ref<IntMatrix__ValueIterator> this_ = new (memset(x10aux::alloc<IntMatrix__ValueIterator>(), 0, sizeof(IntMatrix__ValueIterator))) IntMatrix__ValueIterator();
    this_->_constructor(out__);
    return this_;
}



//#line 515 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": x10.ast.X10MethodDecl_c
void IntMatrix__ValueIterator::__fieldInitializers117(
  ) {
    
    //#line 515 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<IntMatrix__ValueIterator>)this)->
      FMGL(index) = ((x10_int)0);
}
const x10aux::serialization_id_t IntMatrix__ValueIterator::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(IntMatrix__ValueIterator::_deserializer<x10::lang::Reference>);

void IntMatrix__ValueIterator::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(index));
    buf.write(this->FMGL(out__));
    
}

void IntMatrix__ValueIterator::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(index) = buf.read<x10_int>();
    FMGL(out__) = buf.read<x10aux::ref<IntMatrix> >();
}

x10aux::RuntimeType IntMatrix__ValueIterator::rtt;
void IntMatrix__ValueIterator::_initRTT() {
    if (rtt.initStageOne(&rtt)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Object>(), x10aux::getRTT<x10::lang::Iterator<x10_int> >()};
    rtt.initStageTwo("IntMatrix.ValueIterator", 2, parents, 0, NULL, NULL);
}
