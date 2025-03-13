package testedeapi.com.models;
import org.hibernate.annotations.ColumnDefault;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="usuarios")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome", nullable = false, length = 150)
    private String nome;

    @Column(name="email", nullable = false, unique= true, length = 150)
    private String email;

    @Column(name="contato", nullable = true, unique = true, length = 24)
    private String telefone;

    @Column(name="registroAcademico", unique = true, nullable = false, length = 21)
    private String registroAcademico;
    
    @Column(name = "senha", nullable = false, length = 60)
    private String senha;

    @Column(name="ativo", nullable = false, length = 1)
    @ColumnDefault("true")
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Role role;  
    
    }
    

