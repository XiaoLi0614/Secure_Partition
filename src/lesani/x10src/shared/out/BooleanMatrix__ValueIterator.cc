#include <BooleanMatrix__ValueIterator.h>


#include "BooleanMatrix__ValueIterator.inc"

x10::lang::Iterator<x10_boolean>::itable<BooleanMatrix__ValueIterator >  BooleanMatrix__ValueIterator::_itable_0(&BooleanMatrix__ValueIterator::equals, &BooleanMatrix__ValueIterator::hasNext, &BooleanMatrix__ValueIterator::hashCode, &BooleanMatrix__ValueIterator::next, &BooleanMatrix__ValueIterator::toString, &BooleanMatrix__ValueIterator::typeName);
x10::lang::Any::itable<BooleanMatrix__ValueIterator >  BooleanMatrix__ValueIterator::_itable_1(&BooleanMatrix__ValueIterator::equals, &BooleanMatrix__ValueIterator::hashCode, &BooleanMatrix__ValueIterator::toString, &BooleanMatrix__ValueIterator::typeName);
x10aux::itable_entry BooleanMatrix__ValueIterator::_itables[3] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Iterator<x10_boolean> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<BooleanMatrix__ValueIterator>())};
void BooleanMatrix__ValueIterator::_instance_init() {
    _I_("Doing initialisation for class: BooleanMatrix__ValueIterator");
    
}


//#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": x10.ast.X10FieldDecl_c

//#line 516 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": x10.ast.X10FieldDecl_c

//#line 518 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": x10.ast.X10MethodDecl_c
x10_boolean BooleanMatrix__ValueIterator::hasNext() {
    
    //#line 519 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": x10.ast.X10Return_c
    return (!x10aux::struct_equals(((x10aux::ref<BooleanMatrix__ValueIterator>)this)->
                                     FMGL(index), x10aux::nullCheck(((x10aux::ref<BooleanMatrix__ValueIterator>)this)->
                                                                      FMGL(out__))->
                                                    FMGL(m)));
    
}

//#line 521 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": x10.ast.X10MethodDecl_c
x10_boolean BooleanMatrix__ValueIterator::next() {
    
    //#line 522 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": x10.ast.X10LocalDecl_c
    x10_boolean value = x10aux::nullCheck(x10aux::nullCheck(((x10aux::ref<BooleanMatrix__ValueIterator>)this)->
                                                              FMGL(out__))->
                                            FMGL(array))->x10::array::Array<x10_boolean>::apply(
                          ((x10_int)0),
                          ((x10aux::ref<BooleanMatrix__ValueIterator>)this)->
                            FMGL(index));
    
    //#line 523 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<BooleanMatrix__ValueIterator>)this)->
      FMGL(index) = ((x10_int) ((((x10aux::ref<BooleanMatrix__ValueIterator>)this)->
                                   FMGL(index)) + (((x10_int)1))));
    
    //#line 524 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": x10.ast.X10Return_c
    return value;
    
}

//#line 515 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": x10.ast.X10ConstructorDecl_c
void BooleanMatrix__ValueIterator::_constructor(
  x10aux::ref<BooleanMatrix> out__) {
    
    //#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<BooleanMatrix__ValueIterator>)this)->
      FMGL(out__) = out__;
    this->x10::lang::Object::_constructor();
    
    //#line 515 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<BooleanMatrix__ValueIterator>)this)->BooleanMatrix__ValueIterator::__fieldInitializers89();
    
}
x10aux::ref<BooleanMatrix__ValueIterator> BooleanMatrix__ValueIterator::_make(
  x10aux::ref<BooleanMatrix> out__) {
    x10aux::ref<BooleanMatrix__ValueIterator> this_ = new (memset(x10aux::alloc<BooleanMatrix__ValueIterator>(), 0, sizeof(BooleanMatrix__ValueIterator))) BooleanMatrix__ValueIterator();
    this_->_constructor(out__);
    return this_;
}



//#line 515 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": x10.ast.X10MethodDecl_c
void BooleanMatrix__ValueIterator::__fieldInitializers89(
  ) {
    
    //#line 515 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<BooleanMatrix__ValueIterator>)this)->
      FMGL(index) = ((x10_int)0);
}
const x10aux::serialization_id_t BooleanMatrix__ValueIterator::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(BooleanMatrix__ValueIterator::_deserializer<x10::lang::Reference>);

void BooleanMatrix__ValueIterator::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(index));
    buf.write(this->FMGL(out__));
    
}

void BooleanMatrix__ValueIterator::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(index) = buf.read<x10_int>();
    FMGL(out__) = buf.read<x10aux::ref<BooleanMatrix> >();
}

x10aux::RuntimeType BooleanMatrix__ValueIterator::rtt;
void BooleanMatrix__ValueIterator::_initRTT() {
    if (rtt.initStageOne(&rtt)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Object>(), x10aux::getRTT<x10::lang::Iterator<x10_boolean> >()};
    rtt.initStageTwo("BooleanMatrix.ValueIterator", 2, parents, 0, NULL, NULL);
}
