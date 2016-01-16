<?xml version="1.0" encoding="UTF-8"?>
<helpset version="1.0">

	<!-- title -->
	<title>Activiti-Api. - Ayuda</title>

	<!-- maps -->
	<maps>
		<!-- Pagina por defecto -->
		<homeID>Main</homeID>
		<mapref location="ayuda_map.jhm"/>
	</maps>
	<view>
		<name>Tabla de contenidos</name>
		<label>Tabla de contenidos</label>
		<type>javax.help.TOCView</type>
		<data>ayuda_toc.xml</data>
	</view>
	<view>
		<name>Indice</name>
		<label>Indice</label>
		<type>javax.help.IndexView</type>
		<data>ayuda_index.xml</data>
	</view>
	<view>
		<name>Buscar</name>
		<label>Buscar</label>
		<type>javax.help.SearchView</type>
		<data engine="com.sun.java.help.search.DefaultSearchEngine">JavaHelpSearch</data>
	</view>
</helpset>	
