#ifndef __INTMATRIX__VALUEITERABLE_H
#define __INTMATRIX__VALUEITERABLE_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_ITERABLE_H_NODEPS
#include <x10/lang/Iterable.h>
#undef X10_LANG_ITERABLE_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
class IntMatrix;
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
class IntMatrix__ValueIterator;
class IntMatrix__ValueIterable : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Iterable<x10_int>::itable<IntMatrix__ValueIterable > _itable_0;
    
    static x10::lang::Any::itable<IntMatrix__ValueIterable > _itable_1;
    
    void _instance_init();
    
    x10aux::ref<IntMatrix> FMGL(out__);
    
    virtual x10aux::ref<x10::lang::Iterator<x10_int> > iterator();
    void _constructor(x10aux::ref<IntMatrix> out__);
    
    static x10aux::ref<IntMatrix__ValueIterable> _make(x10aux::ref<IntMatrix> out__);
    
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
#endif // INTMATRIX__VALUEITERABLE_H

class IntMatrix__ValueIterable;

#ifndef INTMATRIX__VALUEITERABLE_H_NODEPS
#define INTMATRIX__VALUEITERABLE_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Iterable.h>
#include <x10/lang/Int.h>
#include <IntMatrix.h>
#include <x10/lang/Iterator.h>
#include <IntMatrix__ValueIterator.h>
#ifndef INTMATRIX__VALUEITERABLE_H_GENERICS
#define INTMATRIX__VALUEITERABLE_H_GENERICS
template<class __T> x10aux::ref<__T> IntMatrix__ValueIterable::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<IntMatrix__ValueIterable> this_ = new (memset(x10aux::alloc<IntMatrix__ValueIterable>(), 0, sizeof(IntMatrix__ValueIterable))) IntMatrix__ValueIterable();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // INTMATRIX__VALUEITERABLE_H_GENERICS
#endif // __INTMATRIX__VALUEITERABLE_H_NODEPS
