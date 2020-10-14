#ifndef __TIMER_H
#define __TIMER_H

#include <x10rt17.h>

#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
class Double;
} } 
#include <x10/lang/Double.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
class Transfer;
namespace x10 { namespace lang { 
class System;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(Z3), class FMGL(U)> class Fun_0_3;
} } 
class Timer : public x10::lang::Ref   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::lang::Rail<x10_double > > FMGL(startTimes);
    
    x10aux::ref<x10::lang::Rail<x10_double > > FMGL(elapsedTimes);
    
    x10aux::ref<x10::lang::Rail<x10_double > > FMGL(totalTimes);
    
    void _constructor(x10_int n);
    
    static x10aux::ref<Timer> _make(x10_int n);
    
    void _constructor();
    
    static x10aux::ref<Timer> _make();
    
    virtual void start(x10_int n);
    virtual void start();
    virtual void stop(x10_int n);
    virtual void stop();
    virtual x10_double readTimer(x10_int n);
    virtual x10_double readTimer();
    virtual void resetTimer(x10_int n);
    virtual void resetTimer();
    virtual void resetAllTimers();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf, x10aux::addr_map& m);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
#endif // TIMER_H

class Timer;

#ifndef TIMER_H_NODEPS
#define TIMER_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Double.h>
#include <x10/lang/Int.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <Transfer.h>
#include <x10/lang/System.h>
#include <x10/lang/Fun_0_3.h>
#ifndef TIMER_H_GENERICS
#define TIMER_H_GENERICS
template<class __T> x10aux::ref<__T> Timer::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<Timer> this_ = new (x10aux::alloc_remote<Timer>()) Timer();
    this_->_deserialize_body(buf);
    return this_;
}

#endif // TIMER_H_GENERICS
#endif // __TIMER_H_NODEPS
