#ifndef __MAPREDUCELAUNCHER__HASHSET_H
#define __MAPREDUCELAUNCHER__HASHSET_H

#include <x10rt17.h>

#define X10_UTIL_HASHSET_H_NODEPS
#include <x10/util/HashSet.h>
#undef X10_UTIL_HASHSET_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
template<class FMGL(T)> class MapReduceLauncher__HashSet;
template <> class MapReduceLauncher__HashSet<void>;
template<class FMGL(T)> class MapReduceLauncher__HashSet : public x10::util::HashSet<FMGL(T)>
  {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[5];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static typename x10::util::Container<FMGL(T)>::template itable<MapReduceLauncher__HashSet<FMGL(T)> > _itable_0;
    
    static typename x10::lang::Iterable<FMGL(T)>::template itable<MapReduceLauncher__HashSet<FMGL(T)> > _itable_1;
    
    static typename x10::util::Collection<FMGL(T)>::template itable<MapReduceLauncher__HashSet<FMGL(T)> > _itable_2;
    
    static typename x10::util::Set<FMGL(T)>::template itable<MapReduceLauncher__HashSet<FMGL(T)> > _itable_3;
    
    void _instance_init();
    
    virtual x10aux::ref<x10::lang::Iterator<FMGL(T)> > iterator();
    void _constructor();
    
    static x10aux::ref<MapReduceLauncher__HashSet<FMGL(T)> > _make(
             );
    
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf, x10aux::addr_map& m);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class MapReduceLauncher__HashSet<void> : public x10::util::HashSet<void>
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};
#endif // MAPREDUCELAUNCHER__HASHSET_H

template<class FMGL(T)>
class MapReduceLauncher__HashSet;

#ifndef MAPREDUCELAUNCHER__HASHSET_H_NODEPS
#define MAPREDUCELAUNCHER__HASHSET_H_NODEPS
#include <x10/util/HashSet.h>
#include <x10/lang/Iterator.h>
#ifndef MAPREDUCELAUNCHER__HASHSET_H_GENERICS
#define MAPREDUCELAUNCHER__HASHSET_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> MapReduceLauncher__HashSet<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<MapReduceLauncher__HashSet<FMGL(T)> > this_ = new (x10aux::alloc_remote<MapReduceLauncher__HashSet<FMGL(T)> >()) MapReduceLauncher__HashSet<FMGL(T)>();
    this_->_deserialize_body(buf);
    return this_;
}

#endif // MAPREDUCELAUNCHER__HASHSET_H_GENERICS
#ifndef MAPREDUCELAUNCHER__HASHSET_H_IMPLEMENTATION
#define MAPREDUCELAUNCHER__HASHSET_H_IMPLEMENTATION
#include <MapReduceLauncher__HashSet.h>


#include "MapReduceLauncher__HashSet.inc"

template<class FMGL(T)> typename x10::util::Container<FMGL(T)>::template itable<MapReduceLauncher__HashSet<FMGL(T)> >  MapReduceLauncher__HashSet<FMGL(T)>::_itable_0(&MapReduceLauncher__HashSet<FMGL(T)>::clone, &MapReduceLauncher__HashSet<FMGL(T)>::contains, &MapReduceLauncher__HashSet<FMGL(T)>::containsAll, &MapReduceLauncher__HashSet<FMGL(T)>::isEmpty, &MapReduceLauncher__HashSet<FMGL(T)>::iterator, &MapReduceLauncher__HashSet<FMGL(T)>::size, &MapReduceLauncher__HashSet<FMGL(T)>::toRail, &MapReduceLauncher__HashSet<FMGL(T)>::toValRail);
template<class FMGL(T)> typename x10::lang::Iterable<FMGL(T)>::template itable<MapReduceLauncher__HashSet<FMGL(T)> >  MapReduceLauncher__HashSet<FMGL(T)>::_itable_1(&MapReduceLauncher__HashSet<FMGL(T)>::iterator);
template<class FMGL(T)> typename x10::util::Collection<FMGL(T)>::template itable<MapReduceLauncher__HashSet<FMGL(T)> >  MapReduceLauncher__HashSet<FMGL(T)>::_itable_2(&MapReduceLauncher__HashSet<FMGL(T)>::add, &MapReduceLauncher__HashSet<FMGL(T)>::addAll, &MapReduceLauncher__HashSet<FMGL(T)>::addAllWhere, &MapReduceLauncher__HashSet<FMGL(T)>::clear, &MapReduceLauncher__HashSet<FMGL(T)>::clone, &MapReduceLauncher__HashSet<FMGL(T)>::contains, &MapReduceLauncher__HashSet<FMGL(T)>::containsAll, &MapReduceLauncher__HashSet<FMGL(T)>::isEmpty, &MapReduceLauncher__HashSet<FMGL(T)>::iterator, &MapReduceLauncher__HashSet<FMGL(T)>::remove, &MapReduceLauncher__HashSet<FMGL(T)>::removeAll, &MapReduceLauncher__HashSet<FMGL(T)>::removeAllWhere, &MapReduceLauncher__HashSet<FMGL(T)>::retainAll, &MapReduceLauncher__HashSet<FMGL(T)>::size, &MapReduceLauncher__HashSet<FMGL(T)>::toRail, &MapReduceLauncher__HashSet<FMGL(T)>::toValRail);
template<class FMGL(T)> typename x10::util::Set<FMGL(T)>::template itable<MapReduceLauncher__HashSet<FMGL(T)> >  MapReduceLauncher__HashSet<FMGL(T)>::_itable_3(&MapReduceLauncher__HashSet<FMGL(T)>::add, &MapReduceLauncher__HashSet<FMGL(T)>::addAll, &MapReduceLauncher__HashSet<FMGL(T)>::addAllWhere, &MapReduceLauncher__HashSet<FMGL(T)>::clear, &MapReduceLauncher__HashSet<FMGL(T)>::clone, &MapReduceLauncher__HashSet<FMGL(T)>::contains, &MapReduceLauncher__HashSet<FMGL(T)>::containsAll, &MapReduceLauncher__HashSet<FMGL(T)>::isEmpty, &MapReduceLauncher__HashSet<FMGL(T)>::iterator, &MapReduceLauncher__HashSet<FMGL(T)>::remove, &MapReduceLauncher__HashSet<FMGL(T)>::removeAll, &MapReduceLauncher__HashSet<FMGL(T)>::removeAllWhere, &MapReduceLauncher__HashSet<FMGL(T)>::retainAll, &MapReduceLauncher__HashSet<FMGL(T)>::size, &MapReduceLauncher__HashSet<FMGL(T)>::toRail, &MapReduceLauncher__HashSet<FMGL(T)>::toValRail);
template<class FMGL(T)> x10aux::itable_entry MapReduceLauncher__HashSet<FMGL(T)>::_itables[5] = {x10aux::itable_entry(x10aux::getRTT<x10::util::Container<FMGL(T)> >(), &_itable_0), x10aux::itable_entry(x10aux::getRTT<x10::lang::Iterable<FMGL(T)> >(), &_itable_1), x10aux::itable_entry(x10aux::getRTT<x10::util::Collection<FMGL(T)> >(), &_itable_2), x10aux::itable_entry(x10aux::getRTT<x10::util::Set<FMGL(T)> >(), &_itable_3), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<MapReduceLauncher__HashSet<FMGL(T)> >())};
template<class FMGL(T)> void MapReduceLauncher__HashSet<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: MapReduceLauncher__HashSet<FMGL(T)>");
    
}


//#line 191 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
template<class FMGL(T)> x10aux::ref<x10::lang::Iterator<FMGL(T)> > MapReduceLauncher__HashSet<FMGL(T)>::iterator(
  ) {
    
    //#line 192 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    return x10aux::null;
    
}

//#line 190 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
template<class FMGL(T)> void MapReduceLauncher__HashSet<FMGL(T)>::_constructor(
                          ) {
    this->x10::util::HashSet<FMGL(T)>::_constructor();
    
}template<class FMGL(T)> x10aux::ref<MapReduceLauncher__HashSet<FMGL(T)> > MapReduceLauncher__HashSet<FMGL(T)>::_make(
                           ) {
    x10aux::ref<MapReduceLauncher__HashSet<FMGL(T)> > this_ = new (x10aux::alloc<MapReduceLauncher__HashSet<FMGL(T)> >()) MapReduceLauncher__HashSet<FMGL(T)>();
    this_->_constructor();
    return this_;
}


template<class FMGL(T)> const x10aux::serialization_id_t MapReduceLauncher__HashSet<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(MapReduceLauncher__HashSet<FMGL(T)>::template _deserializer<x10::lang::Ref>);

template<class FMGL(T)> void MapReduceLauncher__HashSet<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf, x10aux::addr_map& m) {
    x10::util::HashSet<FMGL(T)>::_serialize_body(buf, m);
    
}

template<class FMGL(T)> void MapReduceLauncher__HashSet<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::util::HashSet<FMGL(T)>::_deserialize_body(buf);
    
}

template<class FMGL(T)> x10aux::RuntimeType MapReduceLauncher__HashSet<FMGL(T)>::rtt;
template<class FMGL(T)> void MapReduceLauncher__HashSet<FMGL(T)>::_initRTT() {
    rtt.canonical = &rtt;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::util::HashSet<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const x10aux::RuntimeType *canonical = x10aux::getRTT<MapReduceLauncher__HashSet<void> >();
    const char *name = 
        x10aux::alloc_printf("MapReduceLauncher$HashSet[%s]", params[0]->name()
                             );
    rtt.init(canonical, name, 1, parents, 1, params, variances);
}
#endif // MAPREDUCELAUNCHER__HASHSET_H_IMPLEMENTATION
#endif // __MAPREDUCELAUNCHER__HASHSET_H_NODEPS
