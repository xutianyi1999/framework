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

    private String typeName;

    private String mapping;

    private String description;

    @Transient
    private Map<String, String> mappingJsonObject;
}
