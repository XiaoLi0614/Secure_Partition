#ifndef __TRANSFER_H
#define __TRANSFER_H

#include <x10rt17.h>

#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace runtime { 
class Runtime;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Box;
} } 
class Transfer : public x10::lang::Ref   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    template<class FMGL(T)> static FMGL(T) transfer(FMGL(T) t);
    void _constructor();
    
    static x10aux::ref<Transfer> _make();
    
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf, x10aux::addr_map& m);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
#endif // TRANSFER_H

class Transfer;

#ifndef TRANSFER_H_NODEPS
#define TRANSFER_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/runtime/Runtime.h>
#include <x10/lang/Box.h>
#ifndef TRANSFER_H_GENERICS
#define TRANSFER_H_GENERICS
#ifndef TRANSFER_H_transfer_0
#define TRANSFER_H_transfer_0
template<class FMGL(T)> FMGL(T) Transfer::transfer(FMGL(T) t) {
    
    //#line 6 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    return x10aux::nullCheck((x10aux::class_cast<x10aux::ref<x10::lang::Box<FMGL(T)> > >(t)))->
             FMGL(value);
    
}
#endif // TRANSFER_H_transfer_0
template<class __T> x10aux::ref<__T> Transfer::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<Transfer> this_ = new (x10aux::alloc_remote<Transfer>()) Transfer();
    this_->_deserialize_body(buf);
    return this_;
}

#endif // TRANSFER_H_GENERICS
#endif // __TRANSFER_H_NODEPS
