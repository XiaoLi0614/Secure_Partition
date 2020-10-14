#include <DoubleMatrix__ValueIterable.h>


#include "DoubleMatrix__ValueIterable.inc"

x10::lang::Iterable<x10_double>::itable<DoubleMatrix__ValueIterable >  DoubleMatrix__ValueIterable::_itable_0(&DoubleMatrix__ValueIterable::equals, &DoubleMatrix__ValueIterable::hashCode, &DoubleMatrix__ValueIterable::iterator, &DoubleMatrix__ValueIterable::toString, &DoubleMatrix__ValueIterable::typeName);
x10::lang::Any::itable<DoubleMatrix__ValueIterable >  DoubleMatrix__ValueIterable::_itable_1(&DoubleMatrix__ValueIterable::equals, &DoubleMatrix__ValueIterable::hashCode, &DoubleMatrix__ValueIterable::toString, &DoubleMatrix__ValueIterable::typeName);
x10aux::itable_entry DoubleMatrix__ValueIterable::_itables[3] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Iterable<x10_double> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<DoubleMatrix__ValueIterable>())};
void DoubleMatrix__ValueIterable::_instance_init() {
    _I_("Doing initialisation for class: DoubleMatrix__ValueIterable");
    
}


//#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10FieldDecl_c

//#line 511 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<x10::lang::Iterator<x10_double> > DoubleMatrix__ValueIterable::iterator(
  ) {
    
    //#line 512 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Iterator<x10_double> > >(DoubleMatrix__ValueIterator::_make(((x10aux::ref<DoubleMatrix__ValueIterable>)this)->
                                                                                                                             FMGL(out__)));
    
}

//#line 510 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10ConstructorDecl_c
void DoubleMatrix__ValueIterable::_constructor(x10aux::ref<DoubleMatrix> out__)
{
    
    //#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<DoubleMatrix__ValueIterable>)this)->
      FMGL(out__) =
      out__;
    this->x10::lang::Object::_constructor();
    {
     
    }
    
}
x10aux::ref<DoubleMatrix__ValueIterable> DoubleMatrix__ValueIterable::_make(
  x10aux::ref<DoubleMatrix> out__)
{
    x10aux::ref<DoubleMatrix__ValueIterable> this_ = new (memset(x10aux::alloc<DoubleMatrix__ValueIterable>(), 0, sizeof(DoubleMatrix__ValueIterable))) DoubleMatrix__ValueIterable();
    this_->_constructor(out__);
    return this_;
}


const x10aux::serialization_id_t DoubleMatrix__ValueIterable::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__ValueIterable::_deserializer<x10::lang::Reference>);

void DoubleMatrix__ValueIterable::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(out__));
    
}

void DoubleMatrix__ValueIterable::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(out__) = buf.read<x10aux::ref<DoubleMatrix> >();
}

x10aux::RuntimeType DoubleMatrix__ValueIterable::rtt;
void DoubleMatrix__ValueIterable::_initRTT() {
    if (rtt.initStageOne(&rtt)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Object>(), x10aux::getRTT<x10::lang::Iterable<x10_double> >()};
    rtt.initStageTwo("DoubleMatrix.ValueIterable", 2, parents, 0, NULL, NULL);
}
