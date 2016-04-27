package es.caib.rolsac.api.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ibit.rol.sac.model.TraduccionTratamiento;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;

	public class UnidadAdministrativaDTO implements Serializable{
		
		private static final long serialVersionUID = -7823804176689170293L;
		
		
		private Long id;
		private String businessKey;
		private String claveHita;
		private String dominio;
		
		private long orden;
		private Integer validacion;
		private String responsable;
		private String responsableEmail;
		private String telefono;
		private String fax;
		private String email;
		private Integer sexoResponsable;
		private Integer numfoto1;
		private Integer numfoto2;
		private Integer numfoto3;
		private Integer numfoto4;
		
		private Long fotop;
		private Long fotog;
		private Long logoh;
		private Long logov;
		private Long logos;
		private Long logot;
		
		private String codigoEstandar;
		private Long padreId;

		//private Long[] hijosIds;
		private UnidadAdministrativaDTO[] hijos;
		private String tratamiento;
		private Date fechaUltimaActualizacion;
		
		private String nombre;
		private String presentacion;
		private String abreviatura;
		private String url;

		public UnidadAdministrativaDTO(Long id, String codigoEstandar, String nombre, long orden) {
			this.id = id;
			this.codigoEstandar = codigoEstandar;
			this.nombre = nombre;
			this.orden = orden;
		}
		
		public UnidadAdministrativaDTO(UnidadAdministrativa ua, String idioma, boolean popularHijos) {

			this.id = ua.getId();
			this.businessKey = ua.getBusinessKey();
			this.claveHita = ua.getClaveHita();
			this.dominio = ua.getDominio();
			
			this.orden = ua.getOrden();
			this.validacion = ua.getValidacion();
			this.responsable = ua.getResponsable();
			this.responsableEmail = ua.getResponsableEmail();
			this.telefono = ua.getTelefono();
			this.fax = ua.getFax();
			this.email = ua.getEmail();
			this.sexoResponsable = ua.getSexoResponsable();
			this.numfoto1 = ua.getNumfoto1();
			this.numfoto2 = ua.getNumfoto2();
			this.numfoto3 = ua.getNumfoto3();
			this.numfoto4 = ua.getNumfoto4();

			/*
			this.fotop = ua.getFotop();
			this.fotog = ua.getFotog();
			this.logoh = ua.getLogoh();
			this.logov = ua.getLogov();
			this.logos = ua.getLogos();
			this.logot = ua.getLogot();
			*/
			if (ua.getFotop()!=null) this.fotop = ua.getFotop().getId();
			if (ua.getFotog()!=null) this.fotog = ua.getFotog().getId();
			if (ua.getLogoh()!=null) this.logoh = ua.getLogoh().getId();
			if (ua.getLogov()!=null) this.logov = ua.getLogov().getId();
			if (ua.getLogos()!=null) this.logos = ua.getLogos().getId();
			if (ua.getLogot()!=null) this.logot = ua.getLogot().getId();

			this.codigoEstandar = ua.getCodigoEstandar();

			UnidadAdministrativa padre = ua.getPadre();
			if (padre!=null) {
				this.padreId = padre.getId();
			}

			if (popularHijos) {
				List<UnidadAdministrativa> hijos = ua.getHijos();
				//ArrayList<Long> hijosList = new ArrayList<Long>();
				ArrayList<UnidadAdministrativaDTO> hijosList = new ArrayList<UnidadAdministrativaDTO>();
				for (UnidadAdministrativa hijo:hijos) {
					//hijosList.add(hijo.getId());
					hijosList.add( new UnidadAdministrativaDTO(hijo, idioma, false));
				}

				this.hijos = new UnidadAdministrativaDTO[hijosList.size()];
				this.hijos = hijosList.toArray(this.hijos);
			
			}

			if (this.sexoResponsable != null) {
				TraduccionTratamiento trad = (TraduccionTratamiento)ua.getTratamiento().getTraduccion(idioma);
		    	if (trad == null) {
		    		trad = (TraduccionTratamiento)ua.getTratamiento().getTraduccion();
		    	}
				if (this.sexoResponsable == 1) {
					if (trad != null) {
						this.tratamiento = trad.getTratamientoM();
					}
				} else {
					if (trad != null) {
						this.tratamiento = trad.getTratamientoF();
					}
				}
			}

			this.fechaUltimaActualizacion = new Date();
			if (ua.getFechaUltimaActualizacion()!=null) 
				this.fechaUltimaActualizacion = ua.getFechaUltimaActualizacion();

			TraduccionUA trad = (TraduccionUA)ua.getTraduccion(idioma);
			if (trad == null) {
				trad = (TraduccionUA)ua.getTraduccion();
			}
			this.nombre = trad.getNombre();
			this.presentacion = trad.getPresentacion();
			this.abreviatura = trad.getAbreviatura();
			this.url = trad.getUrl();
		}

		public UnidadAdministrativaDTO() {
			
		}
		
		public UnidadAdministrativaDTO(UnidadAdministrativa ua, String idioma) {
			this (ua, idioma, true);
		}

		public Long getId() {
			return id;
		}

		public String getBusinessKey() {
			return businessKey;
		}

		public String getClaveHita() {
			return claveHita;
		}

		public String getDominio() {
			return dominio;
		}

		public long getOrden() {
			return orden;
		}

		public Integer getValidacion() {
			return validacion;
		}

		public String getResponsable() {
			return responsable;
		}

		public String getTelefono() {
			return telefono;
		}

		public String getFax() {
			return fax;
		}

		public String getEmail() {
			return email;
		}

		public Integer getSexoResponsable() {
			return sexoResponsable;
		}

		public Integer getNumfoto1() {
			return numfoto1;
		}

		public Integer getNumfoto2() {
			return numfoto2;
		}

		public Integer getNumfoto3() {
			return numfoto3;
		}

		public Integer getNumfoto4() {
			return numfoto4;
		}

		/*
		public Archivo getFotop() {
			return fotop;
		}
		
		public Archivo getFotog() {
			return fotog;
		}

		public Archivo getLogoh() {
			return logoh;
		}

		public Archivo getLogov() {
			return logov;
		}

		public Archivo getLogos() {
			return logos;
		}

		public Archivo getLogot() {
			return logot;
		}*/
		
		public Long getFotop() {
			return fotop;
		}
		
		public Long getFotog() {
			return fotog;
		}

		public Long getLogoh() {
			return logoh;
		}

		public Long getLogov() {
			return logov;
		}

		public Long getLogos() {
			return logos;
		}

		public Long getLogot() {
			return logot;
		}

		public String getCodigoEstandar() {
			return codigoEstandar;
		}
		
		public Long getPadreId() {
			return padreId;
		}
		
//		public Long[] getHijosIds() {
//			return hijosIds;
//		}
		
		public UnidadAdministrativaDTO[] getHijos() {
			return hijos;
		}

		public String getTratamiento() {
			return tratamiento;
		}

		public Date getFechaUltimaActualizacion() {
			return fechaUltimaActualizacion;
		}
		
		public String getNombre() {
			return nombre;
		}
		
		public String getPresentacion() {
			return presentacion;
		}
		
		public String getAbreviatura() {
			return abreviatura;
		}
		
		public String getUrl() {
			return url;
		}
		public String getResponsableEmail() {
			return responsableEmail;
		}
	}