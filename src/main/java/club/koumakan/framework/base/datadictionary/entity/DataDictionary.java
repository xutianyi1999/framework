package club.koumakan.framework.base.datadictionary.entity;

import club.koumakan.framework.common.abstractapi.FrameworkEntityApi;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
@Table(name = "base_data_dictionary")
public class DataDictionary implements FrameworkEntityApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 类型名称
    private String typeName;
    // 映射json
    private String mapping;
    // 备注
    private String description;

    @Transient
    private Map<String, String> mappingJsonObject;
}
