package club.koumakan.framework.base.datadictionary;

import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DataDictionaryService extends FrameworkServiceApiImpl<DataDictionary> {

    public DataDictionaryService(JpaRepository<DataDictionary, Long> dao) {
        super(dao);
    }

    public Map<String, String> findMappingByTypeName(String typeName) {
        DataDictionary condition = new DataDictionary();
        condition.setTypeName(typeName);

        DataDictionary dataDictionary = super.findOneByCondition(condition, true);
        return dataDictionary.getMappingJsonObject();
    }

    public String findValueByTypeNameAndKey(String typeName, String key) {
        Map<String, String> mapping = findMappingByTypeName(typeName);
        return mapping.get(key);
    }

    @Override
    public void translate(DataDictionary dataDictionary) {
        if (dataDictionary != null) {
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                HashMap<String, String> map = objectMapper.readValue(
                        dataDictionary.getMapping(),
                        new TypeReference<>() {
                        }
                );

                dataDictionary.setMappingJsonObject(map);
            } catch (Exception e) {
                dataDictionary.setMappingJsonObject(new HashMap<>());
                e.printStackTrace();
            }
        }
    }
}
