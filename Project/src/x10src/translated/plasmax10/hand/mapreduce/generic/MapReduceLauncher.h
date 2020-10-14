#ifndef __MAPREDUCELAUNCHER_H
#define __MAPREDUCELAUNCHER_H

#include <x10rt17.h>

#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
class String;
} } 
class Timer;
namespace x10 { namespace io { 
class Console;
} } 
class MapReduceLauncher : public x10::lang::Ref   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    static void main(x10aux::ref<x10::lang::Rail<x10aux::ref<x10::lang::String> > > id5);
    void _constructor();
    
    static x10aux::ref<MapReduceLauncher> _make();
    
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf, x10aux::addr_map& m);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
#endif // MAPREDUCELAUNCHER_H

class MapReduceLauncher;

#ifndef MAPREDUCELAUNCHER_H_NODEPS
#define MAPREDUCELAUNCHER_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Rail.h>
#include <x10/lang/String.h>
#include <Timer.h>
#include <x10/io/Console.h>
#ifndef MAPREDUCELAUNCHER_H_GENERICS
#define MAPREDUCELAUNCHER_H_GENERICS
template<class __T> x10aux::ref<__T> MapReduceLauncher::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<MapReduceLauncher> this_ = new (x10aux::alloc_remote<MapReduceLauncher>()) MapReduceLauncher();
    this_->_deserialize_body(buf);
    return this_;
}

#endif // MAPREDUCELAUNCHER_H_GENERICS
#endif // __MAPREDUCELAUNCHER_H_NODEPS
