#ifndef __BOOLEANMATRIX__VALUEITERATOR_H
#define __BOOLEANMATRIX__VALUEITERATOR_H

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
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
class BooleanMatrix;
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
class BooleanMatrix__ValueIterator : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Iterator<x10_boolean>::itable<BooleanMatrix__ValueIterator > _itable_0;
    
    static x10::lang::Any::itable<BooleanMatrix__ValueIterator > _itable_1;
    
    void _instance_init();
    
    x10aux::ref<BooleanMatrix> FMGL(out__);
    
    x10_int FMGL(index);
    
    virtual x10_boolean hasNext();
    virtual x10_boolean next();
    void _constructor(x10aux::ref<BooleanMatrix> out__);
    
    static x10aux::ref<BooleanMatrix__ValueIterator> _make(x10aux::ref<BooleanMatrix> out__);
    
    void __fieldInitializers89();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
#endif // BOOLEANMATRIX__VALUEITERATOR_H

class BooleanMatrix__ValueIterator;

#ifndef BOOLEANMATRIX__VALUEITERATOR_H_NODEPS
#define BOOLEANMATRIX__VALUEITERATOR_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Boolean.h>
#include <BooleanMatrix.h>
#include <x10/lang/Int.h>
#ifndef BOOLEANMATRIX__VALUEITERATOR_H_GENERICS
#define BOOLEANMATRIX__VALUEITERATOR_H_GENERICS
template<class __T> x10aux::ref<__T> BooleanMatrix__ValueIterator::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<BooleanMatrix__ValueIterator> this_ = new (memset(x10aux::alloc<BooleanMatrix__ValueIterator>(), 0, sizeof(BooleanMatrix__ValueIterator))) BooleanMatrix__ValueIterator();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // BOOLEANMATRIX__VALUEITERATOR_H_GENERICS
#endif // __BOOLEANMATRIX__VALUEITERATOR_H_NODEPS
