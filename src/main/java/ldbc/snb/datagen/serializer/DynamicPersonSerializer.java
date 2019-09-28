/* 
 Copyright (c) 2013 LDBC
 Linked Data Benchmark Council (http://www.ldbcouncil.org)
 
 This file is part of ldbc_snb_datagen.
 
 ldbc_snb_datagen is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.
 
 ldbc_snb_datagen is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public License
 along with ldbc_snb_datagen.  If not, see <http://www.gnu.org/licenses/>.
 
 Copyright (C) 2011 OpenLink Software <bdsmt@openlinksw.com>
 All Rights Reserved.
 
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation;  only Version 2 of the License dated
 June 1991.
 
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.*/
package ldbc.snb.datagen.serializer;

import ldbc.snb.datagen.dictionary.Dictionaries;
import ldbc.snb.datagen.objects.dynamic.relations.Knows;
import ldbc.snb.datagen.objects.dynamic.person.Person;
import ldbc.snb.datagen.objects.dynamic.relations.StudyAt;
import ldbc.snb.datagen.objects.dynamic.relations.WorkAt;
import org.apache.hadoop.conf.Configuration;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by aprat on 10/15/14.
 */
abstract public class DynamicPersonSerializer {

    public void export(final Person person) {

        serialize(person);

        long universityId = Dictionaries.universities.getUniversityFromLocation(person.universityLocationId());
        if ((universityId != -1) && (person.classYear() != -1)) {
            StudyAt studyAt = new StudyAt();
            studyAt.year = person.classYear();
            studyAt.user = person.accountId();
            studyAt.university = universityId;
            serialize(studyAt);
        }

        Iterator<Long> it = person.companies().keySet().iterator();
        while (it.hasNext()) {
            long companyId = it.next();
            WorkAt workAt = new WorkAt();
            workAt.company = companyId;
            workAt.user = person.accountId();
            workAt.year = person.companies().get(companyId);
            serialize(workAt);
        }
    }

    public void export(final Person p, final Knows k) {
        if (p.accountId() < k.to().accountId())
            serialize(p, k);
    }

    abstract public void reset();

    abstract public void initialize(Configuration conf, int reducerId) throws IOException;

    abstract public void close();

    abstract protected void serialize(final Person p);

    abstract protected void serialize(final StudyAt studyAt);

    abstract protected void serialize(final WorkAt workAt);

    abstract protected void serialize(final Person p, final Knows knows);
}