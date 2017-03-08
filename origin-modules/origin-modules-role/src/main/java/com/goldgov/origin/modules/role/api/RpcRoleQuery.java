/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.goldgov.origin.modules.role.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-03-08")
public class RpcRoleQuery implements org.apache.thrift.TBase<RpcRoleQuery, RpcRoleQuery._Fields>, java.io.Serializable, Cloneable, Comparable<RpcRoleQuery> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RpcRoleQuery");

  private static final org.apache.thrift.protocol.TField PAGE_SIZE_FIELD_DESC = new org.apache.thrift.protocol.TField("pageSize", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField CURRENT_PAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("currentPage", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("count", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField MAX_PAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("maxPage", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField MIN_PAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("minPage", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField FIRST_RESULT_FIELD_DESC = new org.apache.thrift.protocol.TField("firstResult", org.apache.thrift.protocol.TType.I32, (short)6);
  private static final org.apache.thrift.protocol.TField RESULT_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("resultList", org.apache.thrift.protocol.TType.LIST, (short)7);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RpcRoleQueryStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RpcRoleQueryTupleSchemeFactory();

  private int pageSize; // required
  private int currentPage; // required
  private long count; // required
  private int maxPage; // required
  private int minPage; // required
  private int firstResult; // required
  private java.util.List<RpcRole> resultList; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PAGE_SIZE((short)1, "pageSize"),
    CURRENT_PAGE((short)2, "currentPage"),
    COUNT((short)3, "count"),
    MAX_PAGE((short)4, "maxPage"),
    MIN_PAGE((short)5, "minPage"),
    FIRST_RESULT((short)6, "firstResult"),
    RESULT_LIST((short)7, "resultList");

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
        case 1: // PAGE_SIZE
          return PAGE_SIZE;
        case 2: // CURRENT_PAGE
          return CURRENT_PAGE;
        case 3: // COUNT
          return COUNT;
        case 4: // MAX_PAGE
          return MAX_PAGE;
        case 5: // MIN_PAGE
          return MIN_PAGE;
        case 6: // FIRST_RESULT
          return FIRST_RESULT;
        case 7: // RESULT_LIST
          return RESULT_LIST;
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
  private static final int __PAGESIZE_ISSET_ID = 0;
  private static final int __CURRENTPAGE_ISSET_ID = 1;
  private static final int __COUNT_ISSET_ID = 2;
  private static final int __MAXPAGE_ISSET_ID = 3;
  private static final int __MINPAGE_ISSET_ID = 4;
  private static final int __FIRSTRESULT_ISSET_ID = 5;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PAGE_SIZE, new org.apache.thrift.meta_data.FieldMetaData("pageSize", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.CURRENT_PAGE, new org.apache.thrift.meta_data.FieldMetaData("currentPage", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.COUNT, new org.apache.thrift.meta_data.FieldMetaData("count", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.MAX_PAGE, new org.apache.thrift.meta_data.FieldMetaData("maxPage", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.MIN_PAGE, new org.apache.thrift.meta_data.FieldMetaData("minPage", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.FIRST_RESULT, new org.apache.thrift.meta_data.FieldMetaData("firstResult", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.RESULT_LIST, new org.apache.thrift.meta_data.FieldMetaData("resultList", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, RpcRole.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RpcRoleQuery.class, metaDataMap);
  }

  public RpcRoleQuery() {
    this.pageSize = 15;

    this.minPage = 1;

    this.firstResult = 0;

  }

  public RpcRoleQuery(
    int pageSize,
    int currentPage,
    long count,
    int maxPage,
    int minPage,
    int firstResult,
    java.util.List<RpcRole> resultList)
  {
    this();
    this.pageSize = pageSize;
    setPageSizeIsSet(true);
    this.currentPage = currentPage;
    setCurrentPageIsSet(true);
    this.count = count;
    setCountIsSet(true);
    this.maxPage = maxPage;
    setMaxPageIsSet(true);
    this.minPage = minPage;
    setMinPageIsSet(true);
    this.firstResult = firstResult;
    setFirstResultIsSet(true);
    this.resultList = resultList;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RpcRoleQuery(RpcRoleQuery other) {
    __isset_bitfield = other.__isset_bitfield;
    this.pageSize = other.pageSize;
    this.currentPage = other.currentPage;
    this.count = other.count;
    this.maxPage = other.maxPage;
    this.minPage = other.minPage;
    this.firstResult = other.firstResult;
    if (other.isSetResultList()) {
      java.util.List<RpcRole> __this__resultList = new java.util.ArrayList<RpcRole>(other.resultList.size());
      for (RpcRole other_element : other.resultList) {
        __this__resultList.add(new RpcRole(other_element));
      }
      this.resultList = __this__resultList;
    }
  }

  public RpcRoleQuery deepCopy() {
    return new RpcRoleQuery(this);
  }

  @Override
  public void clear() {
    this.pageSize = 15;

    setCurrentPageIsSet(false);
    this.currentPage = 0;
    setCountIsSet(false);
    this.count = 0;
    setMaxPageIsSet(false);
    this.maxPage = 0;
    this.minPage = 1;

    this.firstResult = 0;

    this.resultList = null;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
    setPageSizeIsSet(true);
  }

  public void unsetPageSize() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __PAGESIZE_ISSET_ID);
  }

  /** Returns true if field pageSize is set (has been assigned a value) and false otherwise */
  public boolean isSetPageSize() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __PAGESIZE_ISSET_ID);
  }

  public void setPageSizeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __PAGESIZE_ISSET_ID, value);
  }

  public int getCurrentPage() {
    return this.currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
    setCurrentPageIsSet(true);
  }

  public void unsetCurrentPage() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __CURRENTPAGE_ISSET_ID);
  }

  /** Returns true if field currentPage is set (has been assigned a value) and false otherwise */
  public boolean isSetCurrentPage() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __CURRENTPAGE_ISSET_ID);
  }

  public void setCurrentPageIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __CURRENTPAGE_ISSET_ID, value);
  }

  public long getCount() {
    return this.count;
  }

  public void setCount(long count) {
    this.count = count;
    setCountIsSet(true);
  }

  public void unsetCount() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __COUNT_ISSET_ID);
  }

  /** Returns true if field count is set (has been assigned a value) and false otherwise */
  public boolean isSetCount() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __COUNT_ISSET_ID);
  }

  public void setCountIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __COUNT_ISSET_ID, value);
  }

  public int getMaxPage() {
    return this.maxPage;
  }

  public void setMaxPage(int maxPage) {
    this.maxPage = maxPage;
    setMaxPageIsSet(true);
  }

  public void unsetMaxPage() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __MAXPAGE_ISSET_ID);
  }

  /** Returns true if field maxPage is set (has been assigned a value) and false otherwise */
  public boolean isSetMaxPage() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __MAXPAGE_ISSET_ID);
  }

  public void setMaxPageIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __MAXPAGE_ISSET_ID, value);
  }

  public int getMinPage() {
    return this.minPage;
  }

  public void setMinPage(int minPage) {
    this.minPage = minPage;
    setMinPageIsSet(true);
  }

  public void unsetMinPage() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __MINPAGE_ISSET_ID);
  }

  /** Returns true if field minPage is set (has been assigned a value) and false otherwise */
  public boolean isSetMinPage() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __MINPAGE_ISSET_ID);
  }

  public void setMinPageIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __MINPAGE_ISSET_ID, value);
  }

  public int getFirstResult() {
    return this.firstResult;
  }

  public void setFirstResult(int firstResult) {
    this.firstResult = firstResult;
    setFirstResultIsSet(true);
  }

  public void unsetFirstResult() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __FIRSTRESULT_ISSET_ID);
  }

  /** Returns true if field firstResult is set (has been assigned a value) and false otherwise */
  public boolean isSetFirstResult() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __FIRSTRESULT_ISSET_ID);
  }

  public void setFirstResultIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __FIRSTRESULT_ISSET_ID, value);
  }

  public int getResultListSize() {
    return (this.resultList == null) ? 0 : this.resultList.size();
  }

  public java.util.Iterator<RpcRole> getResultListIterator() {
    return (this.resultList == null) ? null : this.resultList.iterator();
  }

  public void addToResultList(RpcRole elem) {
    if (this.resultList == null) {
      this.resultList = new java.util.ArrayList<RpcRole>();
    }
    this.resultList.add(elem);
  }

  public java.util.List<RpcRole> getResultList() {
    return this.resultList;
  }

  public void setResultList(java.util.List<RpcRole> resultList) {
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

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case PAGE_SIZE:
      if (value == null) {
        unsetPageSize();
      } else {
        setPageSize((java.lang.Integer)value);
      }
      break;

    case CURRENT_PAGE:
      if (value == null) {
        unsetCurrentPage();
      } else {
        setCurrentPage((java.lang.Integer)value);
      }
      break;

    case COUNT:
      if (value == null) {
        unsetCount();
      } else {
        setCount((java.lang.Long)value);
      }
      break;

    case MAX_PAGE:
      if (value == null) {
        unsetMaxPage();
      } else {
        setMaxPage((java.lang.Integer)value);
      }
      break;

    case MIN_PAGE:
      if (value == null) {
        unsetMinPage();
      } else {
        setMinPage((java.lang.Integer)value);
      }
      break;

    case FIRST_RESULT:
      if (value == null) {
        unsetFirstResult();
      } else {
        setFirstResult((java.lang.Integer)value);
      }
      break;

    case RESULT_LIST:
      if (value == null) {
        unsetResultList();
      } else {
        setResultList((java.util.List<RpcRole>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case PAGE_SIZE:
      return getPageSize();

    case CURRENT_PAGE:
      return getCurrentPage();

    case COUNT:
      return getCount();

    case MAX_PAGE:
      return getMaxPage();

    case MIN_PAGE:
      return getMinPage();

    case FIRST_RESULT:
      return getFirstResult();

    case RESULT_LIST:
      return getResultList();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case PAGE_SIZE:
      return isSetPageSize();
    case CURRENT_PAGE:
      return isSetCurrentPage();
    case COUNT:
      return isSetCount();
    case MAX_PAGE:
      return isSetMaxPage();
    case MIN_PAGE:
      return isSetMinPage();
    case FIRST_RESULT:
      return isSetFirstResult();
    case RESULT_LIST:
      return isSetResultList();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RpcRoleQuery)
      return this.equals((RpcRoleQuery)that);
    return false;
  }

  public boolean equals(RpcRoleQuery that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_pageSize = true;
    boolean that_present_pageSize = true;
    if (this_present_pageSize || that_present_pageSize) {
      if (!(this_present_pageSize && that_present_pageSize))
        return false;
      if (this.pageSize != that.pageSize)
        return false;
    }

    boolean this_present_currentPage = true;
    boolean that_present_currentPage = true;
    if (this_present_currentPage || that_present_currentPage) {
      if (!(this_present_currentPage && that_present_currentPage))
        return false;
      if (this.currentPage != that.currentPage)
        return false;
    }

    boolean this_present_count = true;
    boolean that_present_count = true;
    if (this_present_count || that_present_count) {
      if (!(this_present_count && that_present_count))
        return false;
      if (this.count != that.count)
        return false;
    }

    boolean this_present_maxPage = true;
    boolean that_present_maxPage = true;
    if (this_present_maxPage || that_present_maxPage) {
      if (!(this_present_maxPage && that_present_maxPage))
        return false;
      if (this.maxPage != that.maxPage)
        return false;
    }

    boolean this_present_minPage = true;
    boolean that_present_minPage = true;
    if (this_present_minPage || that_present_minPage) {
      if (!(this_present_minPage && that_present_minPage))
        return false;
      if (this.minPage != that.minPage)
        return false;
    }

    boolean this_present_firstResult = true;
    boolean that_present_firstResult = true;
    if (this_present_firstResult || that_present_firstResult) {
      if (!(this_present_firstResult && that_present_firstResult))
        return false;
      if (this.firstResult != that.firstResult)
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

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + pageSize;

    hashCode = hashCode * 8191 + currentPage;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(count);

    hashCode = hashCode * 8191 + maxPage;

    hashCode = hashCode * 8191 + minPage;

    hashCode = hashCode * 8191 + firstResult;

    hashCode = hashCode * 8191 + ((isSetResultList()) ? 131071 : 524287);
    if (isSetResultList())
      hashCode = hashCode * 8191 + resultList.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RpcRoleQuery other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetPageSize()).compareTo(other.isSetPageSize());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPageSize()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pageSize, other.pageSize);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetCurrentPage()).compareTo(other.isSetCurrentPage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCurrentPage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.currentPage, other.currentPage);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetCount()).compareTo(other.isSetCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.count, other.count);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetMaxPage()).compareTo(other.isSetMaxPage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMaxPage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.maxPage, other.maxPage);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetMinPage()).compareTo(other.isSetMinPage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMinPage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.minPage, other.minPage);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetFirstResult()).compareTo(other.isSetFirstResult());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFirstResult()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.firstResult, other.firstResult);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RpcRoleQuery(");
    boolean first = true;

    sb.append("pageSize:");
    sb.append(this.pageSize);
    first = false;
    if (!first) sb.append(", ");
    sb.append("currentPage:");
    sb.append(this.currentPage);
    first = false;
    if (!first) sb.append(", ");
    sb.append("count:");
    sb.append(this.count);
    first = false;
    if (!first) sb.append(", ");
    sb.append("maxPage:");
    sb.append(this.maxPage);
    first = false;
    if (!first) sb.append(", ");
    sb.append("minPage:");
    sb.append(this.minPage);
    first = false;
    if (!first) sb.append(", ");
    sb.append("firstResult:");
    sb.append(this.firstResult);
    first = false;
    if (!first) sb.append(", ");
    sb.append("resultList:");
    if (this.resultList == null) {
      sb.append("null");
    } else {
      sb.append(this.resultList);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RpcRoleQueryStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcRoleQueryStandardScheme getScheme() {
      return new RpcRoleQueryStandardScheme();
    }
  }

  private static class RpcRoleQueryStandardScheme extends org.apache.thrift.scheme.StandardScheme<RpcRoleQuery> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RpcRoleQuery struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PAGE_SIZE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.pageSize = iprot.readI32();
              struct.setPageSizeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CURRENT_PAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.currentPage = iprot.readI32();
              struct.setCurrentPageIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.count = iprot.readI64();
              struct.setCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // MAX_PAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.maxPage = iprot.readI32();
              struct.setMaxPageIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // MIN_PAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.minPage = iprot.readI32();
              struct.setMinPageIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // FIRST_RESULT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.firstResult = iprot.readI32();
              struct.setFirstResultIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // RESULT_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.resultList = new java.util.ArrayList<RpcRole>(_list0.size);
                RpcRole _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = new RpcRole();
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
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RpcRoleQuery struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(PAGE_SIZE_FIELD_DESC);
      oprot.writeI32(struct.pageSize);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(CURRENT_PAGE_FIELD_DESC);
      oprot.writeI32(struct.currentPage);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(COUNT_FIELD_DESC);
      oprot.writeI64(struct.count);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(MAX_PAGE_FIELD_DESC);
      oprot.writeI32(struct.maxPage);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(MIN_PAGE_FIELD_DESC);
      oprot.writeI32(struct.minPage);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(FIRST_RESULT_FIELD_DESC);
      oprot.writeI32(struct.firstResult);
      oprot.writeFieldEnd();
      if (struct.resultList != null) {
        oprot.writeFieldBegin(RESULT_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.resultList.size()));
          for (RpcRole _iter3 : struct.resultList)
          {
            _iter3.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RpcRoleQueryTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcRoleQueryTupleScheme getScheme() {
      return new RpcRoleQueryTupleScheme();
    }
  }

  private static class RpcRoleQueryTupleScheme extends org.apache.thrift.scheme.TupleScheme<RpcRoleQuery> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RpcRoleQuery struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetPageSize()) {
        optionals.set(0);
      }
      if (struct.isSetCurrentPage()) {
        optionals.set(1);
      }
      if (struct.isSetCount()) {
        optionals.set(2);
      }
      if (struct.isSetMaxPage()) {
        optionals.set(3);
      }
      if (struct.isSetMinPage()) {
        optionals.set(4);
      }
      if (struct.isSetFirstResult()) {
        optionals.set(5);
      }
      if (struct.isSetResultList()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetPageSize()) {
        oprot.writeI32(struct.pageSize);
      }
      if (struct.isSetCurrentPage()) {
        oprot.writeI32(struct.currentPage);
      }
      if (struct.isSetCount()) {
        oprot.writeI64(struct.count);
      }
      if (struct.isSetMaxPage()) {
        oprot.writeI32(struct.maxPage);
      }
      if (struct.isSetMinPage()) {
        oprot.writeI32(struct.minPage);
      }
      if (struct.isSetFirstResult()) {
        oprot.writeI32(struct.firstResult);
      }
      if (struct.isSetResultList()) {
        {
          oprot.writeI32(struct.resultList.size());
          for (RpcRole _iter4 : struct.resultList)
          {
            _iter4.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RpcRoleQuery struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.pageSize = iprot.readI32();
        struct.setPageSizeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.currentPage = iprot.readI32();
        struct.setCurrentPageIsSet(true);
      }
      if (incoming.get(2)) {
        struct.count = iprot.readI64();
        struct.setCountIsSet(true);
      }
      if (incoming.get(3)) {
        struct.maxPage = iprot.readI32();
        struct.setMaxPageIsSet(true);
      }
      if (incoming.get(4)) {
        struct.minPage = iprot.readI32();
        struct.setMinPageIsSet(true);
      }
      if (incoming.get(5)) {
        struct.firstResult = iprot.readI32();
        struct.setFirstResultIsSet(true);
      }
      if (incoming.get(6)) {
        {
          org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.resultList = new java.util.ArrayList<RpcRole>(_list5.size);
          RpcRole _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = new RpcRole();
            _elem6.read(iprot);
            struct.resultList.add(_elem6);
          }
        }
        struct.setResultListIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

