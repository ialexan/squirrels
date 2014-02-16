package edu.csula.squirrels.web.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import edu.csula.squirrels.model.Species;
import edu.csula.squirrels.model.dao.SpeciesDao;

@Component
@Scope("prototype")
public class SpeciesPropertyEditor extends PropertyEditorSupport {

    @Autowired
    SpeciesDao speciesDao;

    @Override
    public void setAsText( String text ) throws IllegalArgumentException
    {
        if( StringUtils.hasText( text ) )
            setValue( speciesDao.getSpecies( Long.valueOf( text ) ) );
    }

    @Override
    public String getAsText()
    {
        Species species = (Species) getValue();
        return species != null ? species.getId().toString() : "";
    }

}
