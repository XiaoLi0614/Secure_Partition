#ifndef __MAPREDUCER_H
#define __MAPREDUCER_H

#include <x10rt17.h>

#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define MAPPER_H_NODEPS
#include <Mapper.h>
#undef MAPPER_H_NODEPS
#define REDUCER_H_NODEPS
#include <Reducer.h>
#undef REDUCER_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class Set;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class Set;
} } 
namespace x10 { namespace util { 
template<class FMGL(T), class FMGL(U)> class Pair;
} } 
#include <x10/util/Pair.struct_h>
namespace x10 { namespace lang { 
class RuntimeException;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
class Dist;
} } 
namespace x10 { namespace lang { 
class Region;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class Point;
} } 
class Transfer;
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class ClassCastException;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
template<class FMGL(T)> class MapReducer__HashSet;
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class ValRail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace runtime { 
class Runtime;
} } 
namespace x10 { namespace lang { 
class VoidFun_0_0;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class Set;
} } 
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)> class MapReducer;
template <> class MapReducer<void, void, void, void>;
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)> class MapReducer : public x10::lang::Ref
  {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::util::Set<FMGL(T1)> > FMGL(inputs);
    
    x10aux::ref<x10::lang::Array<FMGL(T1)> > FMGL(inputArray);
    
    x10aux::ref<x10::lang::Array<x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > > >
      FMGL(mapPartitionArray);
    
    void _constructor(x10aux::ref<x10::util::Set<FMGL(T1)> > inputs);
    
    void _constructor();
    
    virtual void compute(x10aux::ref<x10::util::Set<FMGL(T1)> > inputs);
    virtual void compute();
    void phases();
    void distributePhase();
    void mapPhase();
    void redistributePhase();
    void reducePhase();
    virtual x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > >
      map(
      FMGL(T1) x) = 0;
    virtual FMGL(T2) reduce(x10aux::ref<x10::util::Set<FMGL(TI)> > id3) = 0;
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf, x10aux::addr_map& m);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class MapReducer<void, void, void, void> : public x10::lang::Ref
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    static x10_int
      FMGL(REDUCER_COUNT);
    
    static void FMGL(REDUCER_COUNT__do_init)();
    static void FMGL(REDUCER_COUNT__init)();
    static volatile x10aux::status FMGL(REDUCER_COUNT__status);
    static x10_int
      FMGL(REDUCER_COUNT__get)() {
        if (FMGL(REDUCER_COUNT__status) != x10aux::INITIALIZED) {
            FMGL(REDUCER_COUNT__init)();
        }
        return MapReducer<void, void, void, void>::FMGL(REDUCER_COUNT);
    }
    static x10aux::ref<x10::lang::Ref>
      FMGL(REDUCER_COUNT__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(REDUCER_COUNT__id);
    
    
};
#endif // MAPREDUCER_H

template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
class MapReducer;

#ifndef MAPREDUCER_H_NODEPS
#define MAPREDUCER_H_NODEPS
#include <x10/lang/Object.h>
#include <Mapper.h>
#include <Reducer.h>
#include <x10/lang/Int.h>
#include <x10/lang/Place.h>
#include <x10/util/Set.h>
#include <x10/lang/Array.h>
#include <x10/lang/Array.h>
#include <x10/lang/Rail.h>
#include <x10/util/Set.h>
#include <x10/util/Pair.h>
#include <x10/lang/RuntimeException.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Dist.h>
#include <x10/lang/Region.h>
#include <x10/lang/Array.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Point.h>
#include <Transfer.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/ClassCastException.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <MapReducer__HashSet.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/ValRail.h>
#include <x10/lang/Rail.h>
#include <x10/runtime/Runtime.h>
#include <x10/lang/VoidFun_0_0.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Throwable.h>
#include <x10/util/Set.h>
#ifndef MAPREDUCER__CLOSURE__3_CLOSURE
#define MAPREDUCER__CLOSURE__3_CLOSURE
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)> class MapReducer__closure__3 : public x10::lang::Value {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <MapReducer__closure__3<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void apply() {
        
        //#line 164 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > hereRail =
          x10aux::nullCheck(saved_this->
                              FMGL(mapPartitionArray))->apply(
            x10::lang::Point::__implicit_convert(
              (x10aux::ref<x10::lang::ValRail<x10_int > >)x10aux::alloc_rail<x10_int,
              x10::lang::ValRail<x10_int > >(1,(x10::runtime::Runtime::here()->
                                                  FMGL(id)))));
        
        //#line 166 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > interms =
          x10aux::nullCheck(saved_this)->map(
            x10aux::nullCheck(saved_this->
                                FMGL(inputArray))->apply(
              (__extension__ ({
                  x10aux::ref<x10::lang::Point> __desugarer__var__1__ =
                    p;
                  
                  //#line 166 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                  x10aux::ref<x10::lang::Point> __var17__;
                  
                  //#line 166 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                  goto __ret18; __ret18: 
                  //#line 166 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                  do
                  {
                  {
                      
                      //#line 166 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                      if ((!x10aux::struct_equals(__desugarer__var__1__,
                                                  x10aux::null)) &&
                            !((x10aux::struct_equals(x10aux::nullCheck(__desugarer__var__1__)->
                                                       FMGL(rank),
                                                     ((x10_int)1)))))
                      {
                          
                          //#line 166 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                          x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10::lang::String::Lit("x10.lang.Point{_self107667.x10.lang.Point#rank==1}"))));
                      }
                      
                      //#line 166 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                      __var17__ =
                        __desugarer__var__1__;
                      
                      //#line 166 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                      goto __ret18_end_;
                      
                  }
                  goto __ret18_next_; __ret18_next_: ;
                  }
                  while (false);
                  goto __ret18_end_; __ret18_end_: ;
                  __var17__;
              }))
              ));
        
        //#line 169 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        x10aux::ref<x10::lang::Rail<x10::util::Pair<FMGL(KI), FMGL(TI)> > > intermsRail =
          (((x10::lang::Object*)(((x10aux::ref<x10::lang::Object>)x10aux::placeCheck(x10aux::nullCheck(interms))).operator->()))->*(x10aux::findITable<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > >(((x10aux::ref<x10::lang::Object>)interms)->_getITables())->toRail))();
        
        //#line 170 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        {
            x10_int i;
            for (
                 //#line 170 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                 i = ((x10_int)0); ((i) < (x10aux::nullCheck(intermsRail)->
                                             FMGL(length)));
                 
                 //#line 170 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                 i +=
                   ((x10_int)1)) {
                
                //#line 171 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                x10::util::Pair<FMGL(KI), FMGL(TI)> interm =
                  (*Transfer::transfer<x10aux::ref<x10::lang::Rail<x10::util::Pair<FMGL(KI), FMGL(TI)> > > >(
                      intermsRail))[i];
                
                //#line 172 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                x10_int index = ((x10aux::hash_code(interm->
                                                      FMGL(first))) % (MapReducer<void, void, void, void>::
                                                                         FMGL(REDUCER_COUNT__get)()));
                
                //#line 173 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                (__extension__ ({ x10aux::ref<x10::lang::Object> _ = x10aux::nullCheck((*Transfer::transfer<x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > >(
                                                                                           hereRail))[index]);
                (((x10::lang::Object*)(_.operator->()))->*(x10aux::findITable<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > >(_->_getITables())->add))(
                  interm); }));
            }
        }
        
    }
    
    // captured environment
    x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> > saved_this;
    x10aux::ref<x10::lang::Point> p;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf, x10aux::addr_map& m) {
        buf.write(this->saved_this, m);
        buf.write(this->p, m);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10aux::ref<MapReducer__closure__3<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > > this_ = new (x10aux::alloc<MapReducer__closure__3<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > >()) MapReducer__closure__3<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >(x10aux::SERIALIZATION_MARKER());
        this_->saved_this = buf.read<x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> > >();
        this_->p = buf.read<x10aux::ref<x10::lang::Point> >();
        return this_;
    }
    
    MapReducer__closure__3(x10aux::SERIALIZATION_MARKER) { }
    
    MapReducer__closure__3(x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> > saved_this, x10aux::ref<x10::lang::Point> p) {
        this->saved_this = saved_this;
        this->p = p;
    }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10::lang::String::Lit("/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translatedbenchs/hand/mapreduce/generic/MapReduceLauncher.x10:163-175");
    }

};

template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
typename x10::lang::VoidFun_0_0::template itable <MapReducer__closure__3<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > >MapReducer__closure__3<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_itable(&MapReducer__closure__3<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::apply);template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
x10aux::itable_entry MapReducer__closure__3<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_itables[2] = {x10aux::itable_entry(&x10::lang::VoidFun_0_0::rtt, &MapReducer__closure__3<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_itable),x10aux::itable_entry(NULL, NULL)};
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
const x10aux::serialization_id_t MapReducer__closure__3<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(MapReducer__closure__3<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::template _deserialize<x10::lang::Ref>);

#endif // MAPREDUCER__CLOSURE__3_CLOSURE
#ifndef MAPREDUCER__CLOSURE__2_CLOSURE
#define MAPREDUCER__CLOSURE__2_CLOSURE
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)> class MapReducer__closure__2 : public x10::lang::Value {
    public:
    
    static typename x10::lang::Fun_0_1<x10_int, x10aux::ref<MapReducer__HashSet<x10::util::Pair<FMGL(KI), FMGL(TI)> > > >::template itable <MapReducer__closure__2<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10aux::ref<MapReducer__HashSet<x10::util::Pair<FMGL(KI), FMGL(TI)> > > apply(x10_int j) {
        
        //#line 151 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        return MapReducer__HashSet<x10::util::Pair<FMGL(KI), FMGL(TI)> >::_make();
        
    }
    
    // captured environment
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf, x10aux::addr_map& m) {
        
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10aux::ref<MapReducer__closure__2<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > > this_ = new (x10aux::alloc<MapReducer__closure__2<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > >()) MapReducer__closure__2<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >(x10aux::SERIALIZATION_MARKER());
        return this_;
    }
    
    MapReducer__closure__2(x10aux::SERIALIZATION_MARKER) { }
    
    MapReducer__closure__2() {
        
    }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10_int, x10aux::ref<MapReducer__HashSet<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10_int, x10aux::ref<MapReducer__HashSet<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10::lang::String::Lit("/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translatedbenchs/hand/mapreduce/generic/MapReduceLauncher.x10:151");
    }

};

template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)> typename x10::lang::Fun_0_1<x10_int, x10aux::ref<MapReducer__HashSet<x10::util::Pair<FMGL(KI), FMGL(TI)> > > >::template itable <MapReducer__closure__2<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > >MapReducer__closure__2<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_itable(&MapReducer__closure__2<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::apply);template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
x10aux::itable_entry MapReducer__closure__2<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_itables[2] = {x10aux::itable_entry(&x10::lang::Fun_0_1<x10_int, x10aux::ref<MapReducer__HashSet<x10::util::Pair<FMGL(KI), FMGL(TI)> > > >::rtt, &MapReducer__closure__2<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_itable),x10aux::itable_entry(NULL, NULL)};
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
const x10aux::serialization_id_t MapReducer__closure__2<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(MapReducer__closure__2<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::template _deserialize<x10::lang::Ref>);

#endif // MAPREDUCER__CLOSURE__2_CLOSURE
#ifndef MAPREDUCER__CLOSURE__1_CLOSURE
#define MAPREDUCER__CLOSURE__1_CLOSURE
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)> class MapReducer__closure__1 : public x10::lang::Value {
    public:
    
    static typename x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > >::template itable <MapReducer__closure__1<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > apply(x10aux::ref<x10::lang::Point> p) {
        
        //#line 151 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        x10aux::ref<x10::lang::Fun_0_1<x10_int, x10aux::ref<MapReducer__HashSet<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > fun =
          x10aux::class_cast<x10aux::ref<x10::lang::Fun_0_1<x10_int, x10aux::ref<MapReducer__HashSet<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > >(x10aux::ref<x10::lang::Fun_0_1<x10_int, x10aux::ref<MapReducer__HashSet<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > >(x10aux::ref<MapReducer__closure__2<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > >(new (x10aux::alloc<x10::lang::Fun_0_1<x10_int, x10aux::ref<MapReducer__HashSet<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > >(sizeof(MapReducer__closure__2<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2)>)))MapReducer__closure__2<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2)>())));
        
        //#line 152 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        x10aux::ref<x10::lang::ValRail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > rail =
          x10::lang::ValRail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > >::make(MapReducer<void, void, void, void>::
                                                                                                          FMGL(REDUCER_COUNT__get)(), x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10_int, x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > >(fun));
        
        //#line 153 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        return x10::lang::Rail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > >::make(rail);
        
    }
    
    // captured environment
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf, x10aux::addr_map& m) {
        
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10aux::ref<MapReducer__closure__1<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > > this_ = new (x10aux::alloc<MapReducer__closure__1<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > >()) MapReducer__closure__1<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >(x10aux::SERIALIZATION_MARKER());
        return this_;
    }
    
    MapReducer__closure__1(x10aux::SERIALIZATION_MARKER) { }
    
    MapReducer__closure__1() {
        
    }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > > >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > > >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10::lang::String::Lit("/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translatedbenchs/hand/mapreduce/generic/MapReduceLauncher.x10:149-154");
    }

};

template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
typename x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > >::template itable <MapReducer__closure__1<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > >MapReducer__closure__1<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_itable(&MapReducer__closure__1<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::apply);template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
x10aux::itable_entry MapReducer__closure__1<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_itables[2] = {x10aux::itable_entry(&x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > >::rtt, &MapReducer__closure__1<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_itable),x10aux::itable_entry(NULL, NULL)};
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
const x10aux::serialization_id_t MapReducer__closure__1<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(MapReducer__closure__1<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::template _deserialize<x10::lang::Ref>);

#endif // MAPREDUCER__CLOSURE__1_CLOSURE
#ifndef MAPREDUCER__CLOSURE__0_CLOSURE
#define MAPREDUCER__CLOSURE__0_CLOSURE
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)> class MapReducer__closure__0 : public x10::lang::Value {
    public:
    
    static typename x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, FMGL(T1)>::template itable <MapReducer__closure__0<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    FMGL(T1) apply(x10aux::ref<x10::lang::Point> id4) {
        
        //#line 139 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        x10_int i = x10aux::nullCheck(id4)->x10::lang::Point::apply(((x10_int)0));
        
        //#line 139 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        return (*Transfer::transfer<x10aux::ref<x10::lang::Rail<FMGL(T1) > > >(
                   inputRail))[i];
        
    }
    
    // captured environment
    x10aux::ref<x10::lang::Rail<FMGL(T1) > > inputRail;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf, x10aux::addr_map& m) {
        buf.write(this->inputRail, m);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10aux::ref<MapReducer__closure__0<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > > this_ = new (x10aux::alloc<MapReducer__closure__0<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > >()) MapReducer__closure__0<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >(x10aux::SERIALIZATION_MARKER());
        this_->inputRail = buf.read<x10aux::ref<x10::lang::Rail<FMGL(T1) > > >();
        return this_;
    }
    
    MapReducer__closure__0(x10aux::SERIALIZATION_MARKER) { }
    
    MapReducer__closure__0(x10aux::ref<x10::lang::Rail<FMGL(T1) > > inputRail) {
        this->inputRail = inputRail;
    }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, FMGL(T1)> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, FMGL(T1)> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10::lang::String::Lit("/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translatedbenchs/hand/mapreduce/generic/MapReduceLauncher.x10:139");
    }

};

template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)> typename x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, FMGL(T1)>::template itable <MapReducer__closure__0<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > >MapReducer__closure__0<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_itable(&MapReducer__closure__0<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::apply);template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
x10aux::itable_entry MapReducer__closure__0<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_itables[2] = {x10aux::itable_entry(&x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, FMGL(T1)>::rtt, &MapReducer__closure__0<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_itable),x10aux::itable_entry(NULL, NULL)};
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
const x10aux::serialization_id_t MapReducer__closure__0<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(MapReducer__closure__0<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) >::template _deserialize<x10::lang::Ref>);

#endif // MAPREDUCER__CLOSURE__0_CLOSURE
#ifndef MAPREDUCER_H_GENERICS
#define MAPREDUCER_H_GENERICS
#endif // MAPREDUCER_H_GENERICS
#ifndef MAPREDUCER_H_IMPLEMENTATION
#define MAPREDUCER_H_IMPLEMENTATION
#include <MapReducer.h>


#include "MapReducer.inc"

template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)> void MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>::_instance_init() {
    _I_("Doing initialisation for class: MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>");
    
}


//#line 103 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"

//#line 105 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"

//#line 106 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"

//#line 107 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"

//#line 109 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)> void MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>::_constructor(
                                                                           x10aux::ref<x10::util::Set<FMGL(T1)> > inputs)
{
    this->x10::lang::Ref::_constructor();
    
    //#line 110 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    ((x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> >)this)->
      FMGL(inputs) =
      inputs;
    
}


//#line 113 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
void MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>::_constructor(
  )
{
    this->x10::lang::Ref::_constructor();
    
}


//#line 116 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
void
  MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>::compute(
  x10aux::ref<x10::util::Set<FMGL(T1)> > inputs) {
    
    //#line 117 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    ((x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> >)this)->
      FMGL(inputs) = inputs;
    
    //#line 118 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::nullCheck(((x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> >)this))->phases();
}

//#line 121 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
void
  MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>::compute(
  ) {
    
    //#line 122 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    if ((x10aux::struct_equals(((x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> >)this)->
                                 FMGL(inputs), x10aux::null)))
    {
        
        //#line 123 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        x10aux::throwException(x10aux::nullCheck(x10::lang::RuntimeException::_make(x10::lang::String::Lit("Input data in needed."))));
    }
    
    //#line 124 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::nullCheck(((x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> >)this))->phases();
}

//#line 127 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
void
  MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>::phases(
  ) {
    
    //#line 128 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::nullCheck(((x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> >)this))->distributePhase();
    
    //#line 129 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::nullCheck(((x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> >)this))->mapPhase();
    
    //#line 130 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::nullCheck(((x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> >)this))->redistributePhase();
    
    //#line 131 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::nullCheck(((x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> >)this))->reducePhase();
}

//#line 134 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
void
  MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>::distributePhase(
  ) {
    
    //#line 135 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::ref<x10::lang::Rail<FMGL(T1) > > inputRail =
      (__extension__ ({ x10aux::ref<x10::lang::Object> _ = x10aux::nullCheck(((x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> >)this)->
                                                                               FMGL(inputs));
      (((x10::lang::Object*)(_.operator->()))->*(x10aux::findITable<x10::util::Set<FMGL(T1)> >(_->_getITables())->toRail))(); }));
    
    //#line 136 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::ref<x10::lang::Dist> inputDist =
      x10::lang::Dist::makeBlock(
        x10::lang::Region::makeRectangular(
          ((x10_int)0),
          ((x10aux::nullCheck(inputRail)->
              FMGL(length)) - (((x10_int)1)))));
    
    //#line 137 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    ((x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> >)this)->
      FMGL(inputArray) = x10::lang::Array<void>::template makeVal<FMGL(T1) >(
                           inputDist,
                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, FMGL(T1)> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, FMGL(T1)> >(x10aux::ref<MapReducer__closure__0<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > >(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, FMGL(T1)> >(sizeof(MapReducer__closure__0<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2)>)))MapReducer__closure__0<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2)>(inputRail)))));
    
    //#line 145 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::ref<x10::lang::Dist> intermediateDist =
      (__extension__ ({
        x10aux::ref<x10::lang::Dist> __desugarer__var__0__ =
          x10::lang::Dist::makeUnique();
        
        //#line 145 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        x10aux::ref<x10::lang::Dist> __var14__;
        
        //#line 145 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        goto __ret15; __ret15: 
        //#line 145 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        do
        {
        {
            
            //#line 145 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
            if ((!x10aux::struct_equals(__desugarer__var__0__,
                                        x10aux::null)) &&
                  !((x10aux::struct_equals(x10aux::nullCheck(__desugarer__var__0__)->x10::lang::Dist::rank(),
                                           ((x10_int)1)))))
            {
                
                //#line 145 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10::lang::String::Lit("x10.lang.Dist{_self89544.x10.lang.Dist#region.x10.lang.Region#rank==1}"))));
            }
            
            //#line 145 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
            __var14__ =
              __desugarer__var__0__;
            
            //#line 145 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
            goto __ret15_end_;
            
        }
        goto __ret15_next_; __ret15_next_: ;
        }
        while (false);
        goto __ret15_end_; __ret15_end_: ;
        __var14__;
    }))
    ;
    
    //#line 146 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    ((x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> >)this)->
      FMGL(mapPartitionArray) = x10::lang::Array<void>::template makeVal<x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > >(
                                  intermediateDist,
                                  x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > > > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > > >(x10aux::ref<MapReducer__closure__1<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > >(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::lang::Point>, x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > > > > >(sizeof(MapReducer__closure__1<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2)>)))MapReducer__closure__1<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2)>()))));
}

//#line 161 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
void
  MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>::mapPhase(
  ) {
    
    //#line 162 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    try {
    try {
        
        //#line 162 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        x10::runtime::Runtime::startFinish();
        {
            {
                
                //#line 163 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                x10aux::ref<x10::lang::Dist> __desugarer__var__2__ =
                  x10aux::nullCheck(((x10aux::ref<MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)> >)this)->
                                      FMGL(inputArray))->
                    FMGL(dist);
                
                //#line 163 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                {
                    x10aux::ref<x10::lang::Object> __ip = (x10aux::nullCheck(__desugarer__var__2__))->iterator();
                    typename x10::lang::Iterator<x10aux::ref<x10::lang::Point> >::template itable<x10::lang::Object> *__ip_itable = x10aux::findITable<x10::lang::Iterator<x10aux::ref<x10::lang::Point> > >(__ip->_getITables());
                    for (; (((x10::lang::Object*)(__ip.operator->()))->*(__ip_itable->hasNext))();
                           ) {
                        x10aux::ref<x10::lang::Point> p;
                        p = (((x10::lang::Object*)(__ip.operator->()))->*(__ip_itable->next))();
                        {
                            
                            //#line 163 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                            x10::runtime::Runtime::runAsync(
                              x10aux::nullCheck(__desugarer__var__2__)->apply(
                                p),
                              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<MapReducer__closure__3<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(MapReducer__closure__3<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2)>)))MapReducer__closure__3<FMGL(T1),FMGL(KI),FMGL(TI),FMGL(T2)>(this, p)))));
                        }
                    }
                }
                
            }
        }
    }
    catch (x10aux::__ref& __ref__19) {
        x10aux::ref<x10::lang::Throwable>& __exc__ref__19 = (x10aux::ref<x10::lang::Throwable>&)__ref__19;
        
        //#line 162 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__19)) {
            x10aux::ref<x10::lang::Throwable> __desugarer__var__3__ =
              static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__19);
            {
                
                //#line 162 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
                x10::runtime::Runtime::pushException(
                  __desugarer__var__3__);
            }
        } else
        throw;
    }
    } catch (...) {
        {
            
            //#line 162 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
            x10::runtime::Runtime::stopFinish();
        }
        throw;
    }
    {
        
        //#line 162 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        x10::runtime::Runtime::stopFinish();
    }
}

//#line 178 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
void
  MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>::redistributePhase(
  ) {
 
}

//#line 182 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
void
  MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>::reducePhase(
  ) {
 
}
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
void MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>::_serialize_body(x10aux::serialization_buffer& buf, x10aux::addr_map& m) {
    x10::lang::Ref::_serialize_body(buf, m);
    
}

template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
void MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Ref::_deserialize_body(buf);
    
}

template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
x10aux::RuntimeType MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>::rtt;
template<class FMGL(T1), class FMGL(KI), class FMGL(TI), class FMGL(T2)>
void MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>::_initRTT() {
    rtt.canonical = &rtt;
    const x10aux::RuntimeType* parents[3] = { x10aux::getRTT<x10::lang::Ref>(), x10aux::getRTT<Mapper<FMGL(T1), FMGL(KI), FMGL(TI)> >(), x10aux::getRTT<Reducer<FMGL(TI), FMGL(T2)> >()};
    const x10aux::RuntimeType* params[4] = { x10aux::getRTT<FMGL(T1)>(), x10aux::getRTT<FMGL(KI)>(), x10aux::getRTT<FMGL(TI)>(), x10aux::getRTT<FMGL(T2)>()};
    x10aux::RuntimeType::Variance variances[4] = { x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant};
    const x10aux::RuntimeType *canonical = x10aux::getRTT<MapReducer<void, void, void, void> >();
    const char *name = 
        x10aux::alloc_printf("MapReducer[%s,%s,%s,%s]", params[0]->name()
                             , params[1]->name()
                             , params[2]->name()
                             , params[3]->name()
                             );
    rtt.init(canonical, name, 3, parents, 4, params, variances);
}
#endif // MAPREDUCER_H_IMPLEMENTATION
#endif // __MAPREDUCER_H_NODEPS
