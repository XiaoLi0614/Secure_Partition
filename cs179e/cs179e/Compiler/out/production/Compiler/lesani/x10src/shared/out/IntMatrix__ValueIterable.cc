#include <IntMatrix__ValueIterable.h>


#include "IntMatrix__ValueIterable.inc"

x10::lang::Iterable<x10_int>::itable<IntMatrix__ValueIterable >  IntMatrix__ValueIterable::_itable_0(&IntMatrix__ValueIterable::equals, &IntMatrix__ValueIterable::hashCode, &IntMatrix__ValueIterable::iterator, &IntMatrix__ValueIterable::toString, &IntMatrix__ValueIterable::typeName);
x10::lang::Any::itable<IntMatrix__ValueIterable >  IntMatrix__ValueIterable::_itable_1(&IntMatrix__ValueIterable::equals, &IntMatrix__ValueIterable::hashCode, &IntMatrix__ValueIterable::toString, &IntMatrix__ValueIterable::typeName);
x10aux::itable_entry IntMatrix__ValueIterable::_itables[3] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Iterable<x10_int> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<IntMatrix__ValueIterable>())};
void IntMatrix__ValueIterable::_instance_init() {
    _I_("Doing initialisation for class: IntMatrix__ValueIterable");
    
}


//#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": x10.ast.X10FieldDecl_c

//#line 511 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<x10::lang::Iterator<x10_int> > IntMatrix__ValueIterable::iterator(
  ) {
    
    //#line 512 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": x10.ast.X10Return_c
    return x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Iterator<x10_int> > >(IntMatrix__ValueIterator::_make(((x10aux::ref<IntMatrix__ValueIterable>)this)->
                                                                                                                       FMGL(out__)));
    
}

//#line 510 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": x10.ast.X10ConstructorDecl_c
void IntMatrix__ValueIterable::_constructor(x10aux::ref<IntMatrix> out__)
{
    
    //#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/IntMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<IntMatrix__ValueIterable>)this)->
      FMGL(out__) =
      out__;
    this->x10::lang::Object::_constructor();
    {
     
    }
    
}
x10aux::ref<IntMatrix__ValueIterable> IntMatrix__ValueIterable::_make(
  x10aux::ref<IntMatrix> out__)
{
    x10aux::ref<IntMatrix__ValueIterable> this_ = new (memset(x10aux::alloc<IntMatrix__ValueIterable>(), 0, sizeof(IntMatrix__ValueIterable))) IntMatrix__ValueIterable();
    this_->_constructor(out__);
    return this_;
}


const x10aux::serialization_id_t IntMatrix__ValueIterable::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(IntMatrix__ValueIterable::_deserializer<x10::lang::Reference>);

void IntMatrix__ValueIterable::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(out__));
    
}

void IntMatrix__ValueIterable::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(out__) = buf.read<x10aux::ref<IntMatrix> >();
}

x10aux::RuntimeType IntMatrix__ValueIterable::rtt;
void IntMatrix__ValueIterable::_initRTT() {
    if (rtt.initStageOne(&rtt)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Object>(), x10aux::getRTT<x10::lang::Iterable<x10_int> >()};
    rtt.initStageTwo("IntMatrix.ValueIterable", 2, parents, 0, NULL, NULL);
}
