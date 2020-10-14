#include <DoubleMatrix__ValueIterator.h>


#include "DoubleMatrix__ValueIterator.inc"

x10::lang::Iterator<x10_double>::itable<DoubleMatrix__ValueIterator >  DoubleMatrix__ValueIterator::_itable_0(&DoubleMatrix__ValueIterator::equals, &DoubleMatrix__ValueIterator::hasNext, &DoubleMatrix__ValueIterator::hashCode, &DoubleMatrix__ValueIterator::next, &DoubleMatrix__ValueIterator::toString, &DoubleMatrix__ValueIterator::typeName);
x10::lang::Any::itable<DoubleMatrix__ValueIterator >  DoubleMatrix__ValueIterator::_itable_1(&DoubleMatrix__ValueIterator::equals, &DoubleMatrix__ValueIterator::hashCode, &DoubleMatrix__ValueIterator::toString, &DoubleMatrix__ValueIterator::typeName);
x10aux::itable_entry DoubleMatrix__ValueIterator::_itables[3] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Iterator<x10_double> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<DoubleMatrix__ValueIterator>())};
void DoubleMatrix__ValueIterator::_instance_init() {
    _I_("Doing initialisation for class: DoubleMatrix__ValueIterator");
    
}


//#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10FieldDecl_c

//#line 516 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10FieldDecl_c

//#line 518 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10_boolean DoubleMatrix__ValueIterator::hasNext() {
    
    //#line 519 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return (!x10aux::struct_equals(((x10aux::ref<DoubleMatrix__ValueIterator>)this)->
                                     FMGL(index), x10aux::nullCheck(((x10aux::ref<DoubleMatrix__ValueIterator>)this)->
                                                                      FMGL(out__))->
                                                    FMGL(m)));
    
}

//#line 521 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10_double DoubleMatrix__ValueIterator::next() {
    
    //#line 522 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_double value = x10aux::nullCheck(x10aux::nullCheck(((x10aux::ref<DoubleMatrix__ValueIterator>)this)->
                                                             FMGL(out__))->
                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                         ((x10_int)0),
                         ((x10aux::ref<DoubleMatrix__ValueIterator>)this)->
                           FMGL(index));
    
    //#line 523 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<DoubleMatrix__ValueIterator>)this)->
      FMGL(index) = ((x10_int) ((((x10aux::ref<DoubleMatrix__ValueIterator>)this)->
                                   FMGL(index)) + (((x10_int)1))));
    
    //#line 524 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return value;
    
}

//#line 515 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10ConstructorDecl_c
void DoubleMatrix__ValueIterator::_constructor(
  x10aux::ref<DoubleMatrix> out__) {
    
    //#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<DoubleMatrix__ValueIterator>)this)->
      FMGL(out__) = out__;
    this->x10::lang::Object::_constructor();
    
    //#line 515 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<DoubleMatrix__ValueIterator>)this)->DoubleMatrix__ValueIterator::__fieldInitializers109();
    
}
x10aux::ref<DoubleMatrix__ValueIterator> DoubleMatrix__ValueIterator::_make(
  x10aux::ref<DoubleMatrix> out__) {
    x10aux::ref<DoubleMatrix__ValueIterator> this_ = new (memset(x10aux::alloc<DoubleMatrix__ValueIterator>(), 0, sizeof(DoubleMatrix__ValueIterator))) DoubleMatrix__ValueIterator();
    this_->_constructor(out__);
    return this_;
}



//#line 515 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
void DoubleMatrix__ValueIterator::__fieldInitializers109(
  ) {
    
    //#line 515 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<DoubleMatrix__ValueIterator>)this)->
      FMGL(index) = ((x10_int)0);
}
const x10aux::serialization_id_t DoubleMatrix__ValueIterator::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__ValueIterator::_deserializer<x10::lang::Reference>);

void DoubleMatrix__ValueIterator::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(index));
    buf.write(this->FMGL(out__));
    
}

void DoubleMatrix__ValueIterator::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(index) = buf.read<x10_int>();
    FMGL(out__) = buf.read<x10aux::ref<DoubleMatrix> >();
}

x10aux::RuntimeType DoubleMatrix__ValueIterator::rtt;
void DoubleMatrix__ValueIterator::_initRTT() {
    if (rtt.initStageOne(&rtt)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Object>(), x10aux::getRTT<x10::lang::Iterator<x10_double> >()};
    rtt.initStageTwo("DoubleMatrix.ValueIterator", 2, parents, 0, NULL, NULL);
}
