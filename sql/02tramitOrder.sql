-- aquest bucle posa un ordre als tramits ja existents

declare 
 contador number;
 begin
 for p in (select pro_codi from rsc_proced) loop
	contador:=0;
	for t in (select tra_codi from rsc_tramit where tra_codpro=p.pro_codi) loop
    --DBMS_OUTPUT.put_line(contador);
		update rsc_tramit set tra_orden=contador where tra_codi=t.tra_codi;
		contador:=contador+1;
	end loop;
 end loop;	
end;