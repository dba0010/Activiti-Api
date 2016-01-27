<?xml version="1.0" encoding="UTF-8"?>
<helpset version="1.0">
	<maps>
		<!-- Pagina por defecto -->
		<homeID>Principal</homeID>
		<mapref location="ayuda_map.jhm"/>
	</maps>
	<view>
		<name>Tabla de contenidos</name>
		<label>Tabla de contenidos</label>
		<type>javax.help.TOCView</type>
		<data>ayuda_toc.xml</data>
	</view>	
	
	<presentation default="true" displayviews="true" displayviewimages="true">
		<title>Activiti-Api. - Ayuda</title>
		<image>AyudaIco</image>
		<toolbar>
				<helpaction image="BackwardIco">javax.help.BackAction</helpaction>
				<helpaction image="ForwardIco">javax.help.ForwardAction</helpaction>
				<helpaction image="PrintIco">javax.help.PrintAction</helpaction>
				<helpaction image="PrintSetupIco">javax.help.PrintSetupAction</helpaction>
		</toolbar>
	</presentation>
</helpset>	
