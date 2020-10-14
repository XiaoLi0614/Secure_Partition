#ifndef __TUPLE2_H
#define __TUPLE2_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
template<class FMGL(T1), class FMGL(T2)> class Tuple2;
template <> class Tuple2<void, void>;
template<class FMGL(T1), class FMGL(T2)> class Tuple2 : public x10::lang::Object
  {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    FMGL(T1) FMGL(_1);
    
    FMGL(T2) FMGL(_2);
    
    void _constructor(FMGL(T1) _1, FMGL(T2) _2);
    
    static x10aux::ref<Tuple2<FMGL(T1), FMGL(T2)> > _make(FMGL(T1) _1, FMGL(T2) _2);
    
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class Tuple2<void, void> : public x10::lang::Object {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};
#endif // TUPLE2_H

template<class FMGL(T1), class FMGL(T2)> class Tuple2;

#ifndef TUPLE2_H_NODEPS
#define TUPLE2_H_NODEPS
#include <x10/lang/Object.h>
#ifndef TUPLE2_H_GENERICS
#define TUPLE2_H_GENERICS
template<class FMGL(T1), class FMGL(T2)> template<class __T> x10aux::ref<__T> Tuple2<FMGL(T1), FMGL(T2)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<Tuple2<FMGL(T1), FMGL(T2)> > this_ = new (memset(x10aux::alloc<Tuple2<FMGL(T1), FMGL(T2)> >(), 0, sizeof(Tuple2<FMGL(T1), FMGL(T2)>))) Tuple2<FMGL(T1), FMGL(T2)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // TUPLE2_H_GENERICS
#ifndef TUPLE2_H_IMPLEMENTATION
#define TUPLE2_H_IMPLEMENTATION
#include <Tuple2.h>


#include "Tuple2.inc"

template<class FMGL(T1), class FMGL(T2)> void Tuple2<FMGL(T1), FMGL(T2)>::_instance_init() {
    _I_("Doing initialisation for class: Tuple2<FMGL(T1), FMGL(T2)>");
    
}


//#line 4 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Tuple2.x10": x10.ast.X10FieldDecl_c

//#line 5 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Tuple2.x10": x10.ast.X10FieldDecl_c

//#line 7 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Tuple2.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T1), class FMGL(T2)> void Tuple2<FMGL(T1), FMGL(T2)>::_constructor(
                                           FMGL(T1) _1,
                                           FMGL(T2) _2) {
    this->x10::lang::Object::_constructor();
    {
     
    }
    
    //#line 8 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Tuple2.x10": polyglot.ast.Eval_c
    ((x10aux::ref<Tuple2<FMGL(T1), FMGL(T2)> >)this)->FMGL(_1) = _1;
    
    //#line 9 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Tuple2.x10": polyglot.ast.Eval_c
    ((x10aux::ref<Tuple2<FMGL(T1), FMGL(T2)> >)this)->FMGL(_2) = _2;
    
}
template<class FMGL(T1), class FMGL(T2)> x10aux::ref<Tuple2<FMGL(T1), FMGL(T2)> > Tuple2<FMGL(T1), FMGL(T2)>::_make(
                                           FMGL(T1) _1,
                                           FMGL(T2) _2) {
    x10aux::ref<Tuple2<FMGL(T1), FMGL(T2)> > this_ = new (memset(x10aux::alloc<Tuple2<FMGL(T1), FMGL(T2)> >(), 0, sizeof(Tuple2<FMGL(T1), FMGL(T2)>))) Tuple2<FMGL(T1), FMGL(T2)>();
    this_->_constructor(_1, _2);
    return this_;
}


template<class FMGL(T1), class FMGL(T2)> const x10aux::serialization_id_t Tuple2<FMGL(T1), FMGL(T2)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(Tuple2<FMGL(T1), FMGL(T2)>::template _deserializer<x10::lang::Reference>);

template<class FMGL(T1), class FMGL(T2)> void Tuple2<FMGL(T1), FMGL(T2)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(_1));
    buf.write(this->FMGL(_2));
    
}

template<class FMGL(T1), class FMGL(T2)> void Tuple2<FMGL(T1), FMGL(T2)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(_1) = buf.read<FMGL(T1)>();
    FMGL(_2) = buf.read<FMGL(T2)>();
}

template<class FMGL(T1), class FMGL(T2)> x10aux::RuntimeType Tuple2<FMGL(T1), FMGL(T2)>::rtt;
template<class FMGL(T1), class FMGL(T2)> void Tuple2<FMGL(T1), FMGL(T2)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<Tuple2<void, void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Object>()};
    const x10aux::RuntimeType* params[2] = { x10aux::getRTT<FMGL(T1)>(), x10aux::getRTT<FMGL(T2)>()};
    x10aux::RuntimeType::Variance variances[2] = { x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant};
    const char *baseName = "Tuple2";
    rtt.initStageTwo(baseName, 1, parents, 2, params, variances);
}
#endif // TUPLE2_H_IMPLEMENTATION
#endif // __TUPLE2_H_NODEPS
