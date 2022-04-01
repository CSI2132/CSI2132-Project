package csi2132.dentist.DentalOffice.model;

public class ProcedureType {
    private Integer procedure_id;
    private String procedure_type_name;
    private String procedure_type_description;

    // getters and setters

    public Integer getProcedure_id() {
        return procedure_id;
    }

    public void setProcedure_id(Integer procedure_id) {
        this.procedure_id = procedure_id;
    }

    public String getProcedure_type_name() {
        return procedure_type_name;
    }

    public void setProcedure_type_name(String procedure_type_name) {
        this.procedure_type_name = procedure_type_name;
    }

    public String getProcedure_type_description() {
        return procedure_type_description;
    }

    public void setProcedure_type_description(String procedure_type_description) {
        this.procedure_type_description = procedure_type_description;
    }
}
