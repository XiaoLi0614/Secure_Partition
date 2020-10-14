#ifndef __INTMATRIX__VALUEITERATOR_H
#define __INTMATRIX__VALUEITERATOR_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_ITERATOR_H_NODEPS
#include <x10/lang/Iterator.h>
#undef X10_LANG_ITERATOR_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
class IntMatrix;
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
class IntMatrix__ValueIterator : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Iterator<x10_int>::itable<IntMatrix__ValueIterator > _itable_0;
    
    static x10::lang::Any::itable<IntMatrix__ValueIterator > _itable_1;
    
    void _instance_init();
    
    x10aux::ref<IntMatrix> FMGL(out__);
    
    x10_int FMGL(index);
    
    virtual x10_boolean hasNext();
    virtual x10_int next();
    void _constructor(x10aux::ref<IntMatrix> out__);
    
    static x10aux::ref<IntMatrix__ValueIterator> _make(x10aux::ref<IntMatrix> out__);
    
    void __fieldInitializers117();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
#endif // INTMATRIX__VALUEITERATOR_H

class IntMatrix__ValueIterator;

#ifndef INTMATRIX__VALUEITERATOR_H_NODEPS
#define INTMATRIX__VALUEITERATOR_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Int.h>
#include <IntMatrix.h>
#include <x10/lang/Boolean.h>
#ifndef INTMATRIX__VALUEITERATOR_H_GENERICS
#define INTMATRIX__VALUEITERATOR_H_GENERICS
template<class __T> x10aux::ref<__T> IntMatrix__ValueIterator::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<IntMatrix__ValueIterator> this_ = new (memset(x10aux::alloc<IntMatrix__ValueIterator>(), 0, sizeof(IntMatrix__ValueIterator))) IntMatrix__ValueIterator();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // INTMATRIX__VALUEITERATOR_H_GENERICS
#endif // __INTMATRIX__VALUEITERATOR_H_NODEPS
