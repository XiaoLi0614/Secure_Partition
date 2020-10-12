#include <BooleanMatrix__ValueIterable.h>


#include "BooleanMatrix__ValueIterable.inc"

x10::lang::Iterable<x10_boolean>::itable<BooleanMatrix__ValueIterable >  BooleanMatrix__ValueIterable::_itable_0(&BooleanMatrix__ValueIterable::equals, &BooleanMatrix__ValueIterable::hashCode, &BooleanMatrix__ValueIterable::iterator, &BooleanMatrix__ValueIterable::toString, &BooleanMatrix__ValueIterable::typeName);
x10::lang::Any::itable<BooleanMatrix__ValueIterable >  BooleanMatrix__ValueIterable::_itable_1(&BooleanMatrix__ValueIterable::equals, &BooleanMatrix__ValueIterable::hashCode, &BooleanMatrix__ValueIterable::toString, &BooleanMatrix__ValueIterable::typeName);
x10aux::itable_entry BooleanMatrix__ValueIterable::_itables[3] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Iterable<x10_boolean> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<BooleanMatrix__ValueIterable>())};
void BooleanMatrix__ValueIterable::_instance_init() {
    _I_("Doing initialisation for class: BooleanMatrix__ValueIterable");
    
}


//#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": x10.ast.X10FieldDecl_c

//#line 511 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<x10::lang::Iterator<x10_boolean> > BooleanMatrix__ValueIterable::iterator(
  ) {
    
    //#line 512 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": x10.ast.X10Return_c
    return x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Iterator<x10_boolean> > >(BooleanMatrix__ValueIterator::_make(((x10aux::ref<BooleanMatrix__ValueIterable>)this)->
                                                                                                                               FMGL(out__)));
    
}

//#line 510 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": x10.ast.X10ConstructorDecl_c
void BooleanMatrix__ValueIterable::_constructor(x10aux::ref<BooleanMatrix> out__)
{
    
    //#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/BooleanMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<BooleanMatrix__ValueIterable>)this)->
      FMGL(out__) =
      out__;
    this->x10::lang::Object::_constructor();
    {
     
    }
    
}
x10aux::ref<BooleanMatrix__ValueIterable> BooleanMatrix__ValueIterable::_make(
  x10aux::ref<BooleanMatrix> out__)
{
    x10aux::ref<BooleanMatrix__ValueIterable> this_ = new (memset(x10aux::alloc<BooleanMatrix__ValueIterable>(), 0, sizeof(BooleanMatrix__ValueIterable))) BooleanMatrix__ValueIterable();
    this_->_constructor(out__);
    return this_;
}


const x10aux::serialization_id_t BooleanMatrix__ValueIterable::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(BooleanMatrix__ValueIterable::_deserializer<x10::lang::Reference>);

void BooleanMatrix__ValueIterable::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(out__));
    
}

void BooleanMatrix__ValueIterable::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(out__) = buf.read<x10aux::ref<BooleanMatrix> >();
}

x10aux::RuntimeType BooleanMatrix__ValueIterable::rtt;
void BooleanMatrix__ValueIterable::_initRTT() {
    if (rtt.initStageOne(&rtt)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Object>(), x10aux::getRTT<x10::lang::Iterable<x10_boolean> >()};
    rtt.initStageTwo("BooleanMatrix.ValueIterable", 2, parents, 0, NULL, NULL);
}
