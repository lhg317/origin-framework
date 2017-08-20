/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.goldgov.origin.modules.organization.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-08-20")
public class RpcOrganizationQuery implements org.apache.thrift.TBase<RpcOrganizationQuery, RpcOrganizationQuery._Fields>, java.io.Serializable, Cloneable, Comparable<RpcOrganizationQuery> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RpcOrganizationQuery");

  private static final org.apache.thrift.protocol.TField PAGING_INFO_FIELD_DESC = new org.apache.thrift.protocol.TField("pagingInfo", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField RESULT_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("resultList", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField SEARCH_PARENT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("searchParentID", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RpcOrganizationQueryStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RpcOrganizationQueryTupleSchemeFactory();

  private com.goldgov.origin.core.service.rpc.RpcPagingInfo pagingInfo; // optional
  private java.util.List<RpcOrganization> resultList; // optional
  private java.lang.String searchParentID; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PAGING_INFO((short)1, "pagingInfo"),
    RESULT_LIST((short)2, "resultList"),
    SEARCH_PARENT_ID((short)3, "searchParentID");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // PAGING_INFO
          return PAGING_INFO;
        case 2: // RESULT_LIST
          return RESULT_LIST;
        case 3: // SEARCH_PARENT_ID
          return SEARCH_PARENT_ID;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.PAGING_INFO,_Fields.RESULT_LIST,_Fields.SEARCH_PARENT_ID};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PAGING_INFO, new org.apache.thrift.meta_data.FieldMetaData("pagingInfo", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, com.goldgov.origin.core.service.rpc.RpcPagingInfo.class)));
    tmpMap.put(_Fields.RESULT_LIST, new org.apache.thrift.meta_data.FieldMetaData("resultList", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, RpcOrganization.class))));
    tmpMap.put(_Fields.SEARCH_PARENT_ID, new org.apache.thrift.meta_data.FieldMetaData("searchParentID", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RpcOrganizationQuery.class, metaDataMap);
  }

  public RpcOrganizationQuery() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RpcOrganizationQuery(RpcOrganizationQuery other) {
    if (other.isSetPagingInfo()) {
      this.pagingInfo = new com.goldgov.origin.core.service.rpc.RpcPagingInfo(other.pagingInfo);
    }
    if (other.isSetResultList()) {
      java.util.List<RpcOrganization> __this__resultList = new java.util.ArrayList<RpcOrganization>(other.resultList.size());
      for (RpcOrganization other_element : other.resultList) {
        __this__resultList.add(new RpcOrganization(other_element));
      }
      this.resultList = __this__resultList;
    }
    if (other.isSetSearchParentID()) {
      this.searchParentID = other.searchParentID;
    }
  }

  public RpcOrganizationQuery deepCopy() {
    return new RpcOrganizationQuery(this);
  }

  @Override
  public void clear() {
    this.pagingInfo = null;
    this.resultList = null;
    this.searchParentID = null;
  }

  public com.goldgov.origin.core.service.rpc.RpcPagingInfo getPagingInfo() {
    return this.pagingInfo;
  }

  public void setPagingInfo(com.goldgov.origin.core.service.rpc.RpcPagingInfo pagingInfo) {
    this.pagingInfo = pagingInfo;
  }

  public void unsetPagingInfo() {
    this.pagingInfo = null;
  }

  /** Returns true if field pagingInfo is set (has been assigned a value) and false otherwise */
  public boolean isSetPagingInfo() {
    return this.pagingInfo != null;
  }

  public void setPagingInfoIsSet(boolean value) {
    if (!value) {
      this.pagingInfo = null;
    }
  }

  public int getResultListSize() {
    return (this.resultList == null) ? 0 : this.resultList.size();
  }

  public java.util.Iterator<RpcOrganization> getResultListIterator() {
    return (this.resultList == null) ? null : this.resultList.iterator();
  }

  public void addToResultList(RpcOrganization elem) {
    if (this.resultList == null) {
      this.resultList = new java.util.ArrayList<RpcOrganization>();
    }
    this.resultList.add(elem);
  }

  public java.util.List<RpcOrganization> getResultList() {
    return this.resultList;
  }

  public void setResultList(java.util.List<RpcOrganization> resultList) {
    this.resultList = resultList;
  }

  public void unsetResultList() {
    this.resultList = null;
  }

  /** Returns true if field resultList is set (has been assigned a value) and false otherwise */
  public boolean isSetResultList() {
    return this.resultList != null;
  }

  public void setResultListIsSet(boolean value) {
    if (!value) {
      this.resultList = null;
    }
  }

  public java.lang.String getSearchParentID() {
    return this.searchParentID;
  }

  public void setSearchParentID(java.lang.String searchParentID) {
    this.searchParentID = searchParentID;
  }

  public void unsetSearchParentID() {
    this.searchParentID = null;
  }

  /** Returns true if field searchParentID is set (has been assigned a value) and false otherwise */
  public boolean isSetSearchParentID() {
    return this.searchParentID != null;
  }

  public void setSearchParentIDIsSet(boolean value) {
    if (!value) {
      this.searchParentID = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case PAGING_INFO:
      if (value == null) {
        unsetPagingInfo();
      } else {
        setPagingInfo((com.goldgov.origin.core.service.rpc.RpcPagingInfo)value);
      }
      break;

    case RESULT_LIST:
      if (value == null) {
        unsetResultList();
      } else {
        setResultList((java.util.List<RpcOrganization>)value);
      }
      break;

    case SEARCH_PARENT_ID:
      if (value == null) {
        unsetSearchParentID();
      } else {
        setSearchParentID((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case PAGING_INFO:
      return getPagingInfo();

    case RESULT_LIST:
      return getResultList();

    case SEARCH_PARENT_ID:
      return getSearchParentID();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case PAGING_INFO:
      return isSetPagingInfo();
    case RESULT_LIST:
      return isSetResultList();
    case SEARCH_PARENT_ID:
      return isSetSearchParentID();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RpcOrganizationQuery)
      return this.equals((RpcOrganizationQuery)that);
    return false;
  }

  public boolean equals(RpcOrganizationQuery that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_pagingInfo = true && this.isSetPagingInfo();
    boolean that_present_pagingInfo = true && that.isSetPagingInfo();
    if (this_present_pagingInfo || that_present_pagingInfo) {
      if (!(this_present_pagingInfo && that_present_pagingInfo))
        return false;
      if (!this.pagingInfo.equals(that.pagingInfo))
        return false;
    }

    boolean this_present_resultList = true && this.isSetResultList();
    boolean that_present_resultList = true && that.isSetResultList();
    if (this_present_resultList || that_present_resultList) {
      if (!(this_present_resultList && that_present_resultList))
        return false;
      if (!this.resultList.equals(that.resultList))
        return false;
    }

    boolean this_present_searchParentID = true && this.isSetSearchParentID();
    boolean that_present_searchParentID = true && that.isSetSearchParentID();
    if (this_present_searchParentID || that_present_searchParentID) {
      if (!(this_present_searchParentID && that_present_searchParentID))
        return false;
      if (!this.searchParentID.equals(that.searchParentID))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetPagingInfo()) ? 131071 : 524287);
    if (isSetPagingInfo())
      hashCode = hashCode * 8191 + pagingInfo.hashCode();

    hashCode = hashCode * 8191 + ((isSetResultList()) ? 131071 : 524287);
    if (isSetResultList())
      hashCode = hashCode * 8191 + resultList.hashCode();

    hashCode = hashCode * 8191 + ((isSetSearchParentID()) ? 131071 : 524287);
    if (isSetSearchParentID())
      hashCode = hashCode * 8191 + searchParentID.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RpcOrganizationQuery other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetPagingInfo()).compareTo(other.isSetPagingInfo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPagingInfo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pagingInfo, other.pagingInfo);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetResultList()).compareTo(other.isSetResultList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResultList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.resultList, other.resultList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSearchParentID()).compareTo(other.isSetSearchParentID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSearchParentID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.searchParentID, other.searchParentID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RpcOrganizationQuery(");
    boolean first = true;

    if (isSetPagingInfo()) {
      sb.append("pagingInfo:");
      if (this.pagingInfo == null) {
        sb.append("null");
      } else {
        sb.append(this.pagingInfo);
      }
      first = false;
    }
    if (isSetResultList()) {
      if (!first) sb.append(", ");
      sb.append("resultList:");
      if (this.resultList == null) {
        sb.append("null");
      } else {
        sb.append(this.resultList);
      }
      first = false;
    }
    if (isSetSearchParentID()) {
      if (!first) sb.append(", ");
      sb.append("searchParentID:");
      if (this.searchParentID == null) {
        sb.append("null");
      } else {
        sb.append(this.searchParentID);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (pagingInfo != null) {
      pagingInfo.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RpcOrganizationQueryStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcOrganizationQueryStandardScheme getScheme() {
      return new RpcOrganizationQueryStandardScheme();
    }
  }

  private static class RpcOrganizationQueryStandardScheme extends org.apache.thrift.scheme.StandardScheme<RpcOrganizationQuery> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RpcOrganizationQuery struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PAGING_INFO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.pagingInfo = new com.goldgov.origin.core.service.rpc.RpcPagingInfo();
              struct.pagingInfo.read(iprot);
              struct.setPagingInfoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // RESULT_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.resultList = new java.util.ArrayList<RpcOrganization>(_list0.size);
                RpcOrganization _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = new RpcOrganization();
                  _elem1.read(iprot);
                  struct.resultList.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setResultListIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SEARCH_PARENT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.searchParentID = iprot.readString();
              struct.setSearchParentIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RpcOrganizationQuery struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.pagingInfo != null) {
        if (struct.isSetPagingInfo()) {
          oprot.writeFieldBegin(PAGING_INFO_FIELD_DESC);
          struct.pagingInfo.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.resultList != null) {
        if (struct.isSetResultList()) {
          oprot.writeFieldBegin(RESULT_LIST_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.resultList.size()));
            for (RpcOrganization _iter3 : struct.resultList)
            {
              _iter3.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.searchParentID != null) {
        if (struct.isSetSearchParentID()) {
          oprot.writeFieldBegin(SEARCH_PARENT_ID_FIELD_DESC);
          oprot.writeString(struct.searchParentID);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RpcOrganizationQueryTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcOrganizationQueryTupleScheme getScheme() {
      return new RpcOrganizationQueryTupleScheme();
    }
  }

  private static class RpcOrganizationQueryTupleScheme extends org.apache.thrift.scheme.TupleScheme<RpcOrganizationQuery> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RpcOrganizationQuery struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetPagingInfo()) {
        optionals.set(0);
      }
      if (struct.isSetResultList()) {
        optionals.set(1);
      }
      if (struct.isSetSearchParentID()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetPagingInfo()) {
        struct.pagingInfo.write(oprot);
      }
      if (struct.isSetResultList()) {
        {
          oprot.writeI32(struct.resultList.size());
          for (RpcOrganization _iter4 : struct.resultList)
          {
            _iter4.write(oprot);
          }
        }
      }
      if (struct.isSetSearchParentID()) {
        oprot.writeString(struct.searchParentID);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RpcOrganizationQuery struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.pagingInfo = new com.goldgov.origin.core.service.rpc.RpcPagingInfo();
        struct.pagingInfo.read(iprot);
        struct.setPagingInfoIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.resultList = new java.util.ArrayList<RpcOrganization>(_list5.size);
          RpcOrganization _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = new RpcOrganization();
            _elem6.read(iprot);
            struct.resultList.add(_elem6);
          }
        }
        struct.setResultListIsSet(true);
      }
      if (incoming.get(2)) {
        struct.searchParentID = iprot.readString();
        struct.setSearchParentIDIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

