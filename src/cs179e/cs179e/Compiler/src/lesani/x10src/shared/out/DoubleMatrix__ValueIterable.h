#ifndef __DOUBLEMATRIX__VALUEITERABLE_H
#define __DOUBLEMATRIX__VALUEITERABLE_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_ITERABLE_H_NODEPS
#include <x10/lang/Iterable.h>
#undef X10_LANG_ITERABLE_H_NODEPS
namespace x10 { namespace lang { 
class Double;
} } 
#include <x10/lang/Double.struct_h>
class DoubleMatrix;
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
class DoubleMatrix__ValueIterator;
class DoubleMatrix__ValueIterable : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Iterable<x10_double>::itable<DoubleMatrix__ValueIterable > _itable_0;
    
    static x10::lang::Any::itable<DoubleMatrix__ValueIterable > _itable_1;
    
    void _instance_init();
    
    x10aux::ref<DoubleMatrix> FMGL(out__);
    
    virtual x10aux::ref<x10::lang::Iterator<x10_double> > iterator();
    void _constructor(x10aux::ref<DoubleMatrix> out__);
    
    static x10aux::ref<DoubleMatrix__ValueIterable> _make(x10aux::ref<DoubleMatrix> out__);
    
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
#endif // DOUBLEMATRIX__VALUEITERABLE_H

class DoubleMatrix__ValueIterable;

#ifndef DOUBLEMATRIX__VALUEITERABLE_H_NODEPS
#define DOUBLEMATRIX__VALUEITERABLE_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Iterable.h>
#include <x10/lang/Double.h>
#include <DoubleMatrix.h>
#include <x10/lang/Iterator.h>
#include <DoubleMatrix__ValueIterator.h>
#ifndef DOUBLEMATRIX__VALUEITERABLE_H_GENERICS
#define DOUBLEMATRIX__VALUEITERABLE_H_GENERICS
template<class __T> x10aux::ref<__T> DoubleMatrix__ValueIterable::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<DoubleMatrix__ValueIterable> this_ = new (memset(x10aux::alloc<DoubleMatrix__ValueIterable>(), 0, sizeof(DoubleMatrix__ValueIterable))) DoubleMatrix__ValueIterable();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // DOUBLEMATRIX__VALUEITERABLE_H_GENERICS
#endif // __DOUBLEMATRIX__VALUEITERABLE_H_NODEPS
