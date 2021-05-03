package club.koumakan.framework.base.datadictionary.service;

import club.koumakan.framework.base.datadictionary.entity.DataDictionary;
import club.koumakan.framework.common.abstractapi.FrameworkServiceApi;

import java.util.Map;

public interface DataDictionaryService extends FrameworkServiceApi<DataDictionary> {

    Map<String, String> findMappingByTypeName(String typeName);

    String findValueByTypeNameAndKey(String typeName, String key);
}
