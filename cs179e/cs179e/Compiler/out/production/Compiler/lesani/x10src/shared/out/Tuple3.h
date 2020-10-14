#ifndef __TUPLE3_H
#define __TUPLE3_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
template<class FMGL(T1), class FMGL(T2), class FMGL(T3)> class Tuple3;
template <> class Tuple3<void, void, void>;
template<class FMGL(T1), class FMGL(T2), class FMGL(T3)> class Tuple3 : public x10::lang::Object
  {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    FMGL(T1) FMGL(_1);
    
    FMGL(T2) FMGL(_2);
    
    FMGL(T3) FMGL(_3);
    
    void _constructor(FMGL(T1) _1, FMGL(T2) _2, FMGL(T3) _3);
    
    static x10aux::ref<Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)> > _make(
             FMGL(T1) _1,
             FMGL(T2) _2,
             FMGL(T3) _3);
    
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class Tuple3<void, void, void> : public x10::lang::Object
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};
#endif // TUPLE3_H

template<class FMGL(T1), class FMGL(T2), class FMGL(T3)>
class Tuple3;

#ifndef TUPLE3_H_NODEPS
#define TUPLE3_H_NODEPS
#include <x10/lang/Object.h>
#ifndef TUPLE3_H_GENERICS
#define TUPLE3_H_GENERICS
template<class FMGL(T1), class FMGL(T2), class FMGL(T3)> template<class __T> x10aux::ref<__T> Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)> > this_ = new (memset(x10aux::alloc<Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)> >(), 0, sizeof(Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)>))) Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // TUPLE3_H_GENERICS
#ifndef TUPLE3_H_IMPLEMENTATION
#define TUPLE3_H_IMPLEMENTATION
#include <Tuple3.h>


#include "Tuple3.inc"

template<class FMGL(T1), class FMGL(T2), class FMGL(T3)> void Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)>::_instance_init() {
    _I_("Doing initialisation for class: Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)>");
    
}


//#line 4 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Tuple3.x10": x10.ast.X10FieldDecl_c

//#line 5 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Tuple3.x10": x10.ast.X10FieldDecl_c

//#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Tuple3.x10": x10.ast.X10FieldDecl_c

//#line 8 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Tuple3.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T1), class FMGL(T2), class FMGL(T3)> void Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)>::_constructor(
                                                           FMGL(T1) _1,
                                                           FMGL(T2) _2,
                                                           FMGL(T3) _3)
{
    this->x10::lang::Object::_constructor();
    {
     
    }
    
    //#line 9 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Tuple3.x10": polyglot.ast.Eval_c
    ((x10aux::ref<Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)> >)this)->
      FMGL(_1) =
      _1;
    
    //#line 10 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Tuple3.x10": polyglot.ast.Eval_c
    ((x10aux::ref<Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)> >)this)->
      FMGL(_2) =
      _2;
    
    //#line 11 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Tuple3.x10": polyglot.ast.Eval_c
    ((x10aux::ref<Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)> >)this)->
      FMGL(_3) =
      _3;
    
}
template<class FMGL(T1), class FMGL(T2), class FMGL(T3)>
x10aux::ref<Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)> > Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)>::_make(
  FMGL(T1) _1,
  FMGL(T2) _2,
  FMGL(T3) _3)
{
    x10aux::ref<Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)> > this_ = new (memset(x10aux::alloc<Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)> >(), 0, sizeof(Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)>))) Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)>();
    this_->_constructor(_1,
    _2,
    _3);
    return this_;
}


template<class FMGL(T1), class FMGL(T2), class FMGL(T3)> const x10aux::serialization_id_t Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)>::template _deserializer<x10::lang::Reference>);

template<class FMGL(T1), class FMGL(T2), class FMGL(T3)> void Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(_1));
    buf.write(this->FMGL(_2));
    buf.write(this->FMGL(_3));
    
}

template<class FMGL(T1), class FMGL(T2), class FMGL(T3)> void Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(_1) = buf.read<FMGL(T1)>();
    FMGL(_2) = buf.read<FMGL(T2)>();
    FMGL(_3) = buf.read<FMGL(T3)>();
}

template<class FMGL(T1), class FMGL(T2), class FMGL(T3)> x10aux::RuntimeType Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)>::rtt;
template<class FMGL(T1), class FMGL(T2), class FMGL(T3)> void Tuple3<FMGL(T1), FMGL(T2), FMGL(T3)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<Tuple3<void, void, void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Object>()};
    const x10aux::RuntimeType* params[3] = { x10aux::getRTT<FMGL(T1)>(), x10aux::getRTT<FMGL(T2)>(), x10aux::getRTT<FMGL(T3)>()};
    x10aux::RuntimeType::Variance variances[3] = { x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant};
    const char *baseName = "Tuple3";
    rtt.initStageTwo(baseName, 1, parents, 3, params, variances);
}
#endif // TUPLE3_H_IMPLEMENTATION
#endif // __TUPLE3_H_NODEPS
