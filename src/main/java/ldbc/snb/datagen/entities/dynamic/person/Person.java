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
package ldbc.snb.datagen.entities.dynamic.person;

import ldbc.snb.datagen.entities.dynamic.relations.Knows;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Person implements Writable {

    private String firstName;
    private String lastName;
    private IP ipAddress;
    private long accountId;
    private long randomId;
    private long creationDate;
    private long deletionDate;
    private long maxNumKnows;
    private long wallId; // ???
    private long birthday;
    private long classYear;
    private int browserId;
    private int countryId;
    private int cityId;
    private int mainInterest;
    private int universityLocationId;
    private byte gender;
    private boolean isLargePoster;
    private TreeSet<Integer> interests;
    private TreeSet<Knows> knows;
    private TreeSet<String> emails;
    private List<Integer> languages;
    private Map<Long, Long> companies;

    public Person() {
        knows = new TreeSet<>();
        emails = new TreeSet<>();
        interests = new TreeSet<>();
        languages = new ArrayList<>();
        companies = new HashMap<>();
        ipAddress = new IP();
    }

    public Person(Person p) {

        firstName = p.firstName();
        lastName = p.lastName();
        accountId = p.accountId();
        creationDate = p.creationDate();
        deletionDate = p.deletionDate();
        browserId = p.browserId();
        countryId = p.countryId();
        cityId = p.cityId();
        wallId = p.wallId();
        mainInterest = p.mainInterest();
        universityLocationId = p.universityLocationId();
        gender = p.gender();
        birthday = p.birthday();
        isLargePoster = p.isLargePoster();
        randomId = p.randomId();
        classYear = p.classYear();
        maxNumKnows = p.maxNumKnows();

        knows = new TreeSet<>();
        emails = new TreeSet<>();
        interests = new TreeSet<>();
        languages = new ArrayList<>();
        companies = new HashMap<>();
        ipAddress = new IP(p.ipAddress());

        for (Knows k : p.knows()) {
            knows.add(new Knows(k));
        }

        interests.addAll(p.interests().descendingSet());
        emails.addAll(p.emails().descendingSet());
        languages.addAll(p.languages());

        for (Map.Entry<Long, Long> c : p.companies().entrySet()) {
            companies.put(c.getKey(), c.getValue());
        }

    }

    public interface PersonSimilarity {
        public float similarity(Person personA, Person personB);
    }


    public void copy(Person p) {
        firstName = p.firstName();
        lastName = p.lastName();
        ipAddress = p.ipAddress();
        accountId = p.accountId();
        randomId = randomId();
        creationDate = creationDate();
        deletionDate = deletionDate();
        maxNumKnows = maxNumKnows();
        wallId = wallId(); // ???
        birthday = birthday();
        classYear = classYear();
        browserId = browserId();
        countryId = countryId();
        cityId = cityId();
        mainInterest = mainInterest();
        universityLocationId = universityLocationId();
        gender = gender();
        isLargePoster = isLargePoster();
        interests = interests();
        knows = knows();
        emails = emails();
        languages = languages();
        companies = companies();
    }

//    // copy method??
//    public static class PersonSummary implements Writable { // subset of Person variables
//        private long accountId_;
//        private long creationDate_;
//        private long deletionDate_;
//        private int browserId_;
//        private int country_;
//        private IP ipAddress_;
//        private boolean isLargePoster_;
//
//        public PersonSummary() {
//            ipAddress_ = new IP();
//        } // person constructors
//
//        public PersonSummary(Person p) {
//            accountId_ = p.accountId();
//            creationDate_ = p.creationDate();
//            deletionDate_ = p.deletionDate();
//            browserId_ = p.browserId();
//            country_ = p.countryId();
//            ipAddress_ = new IP(p.ipAddress());
//            isLargePoster_ = p.isLargePoster();
//        }
//
//        public PersonSummary(PersonSummary p) {
//            accountId_ = p.accountId();
//            creationDate_ = p.creationDate();
//            deletionDate_ = p.deletionDate();
//            browserId_ = p.browserId();
//            country_ = p.countryId();
//            ipAddress_ = new IP(p.ipAddress());
//            isLargePoster_ = p.isLargePoster();
//        }
//
//        public void copy(PersonSummary p) {
//            accountId_ = p.accountId();
//            creationDate_ = p.creationDate();
//            deletionDate_ = p.deletionDate();
//            browserId_ = p.browserId();
//            country_ = p.countryId();
//            ipAddress_ = new IP(p.ipAddress());
//            isLargePoster_ = p.isLargePoster();
//        }
//
//        public long accountId() {
//            return accountId_;
//        }
//
//        public long creationDate() {
//            return creationDate_;
//        }
//
//        public long deletionDate() {
//            return deletionDate_;
//        }
//
//        public int browserId() {
//            return browserId_;
//        }
//
//        public int countryId() {
//            return country_;
//        }
//
//        public IP ipAddress() {
//            return ipAddress_;
//        }
//
//        public boolean isLargePoster() {
//            return isLargePoster_;
//        }
//
//        public void readFields(DataInput arg0) throws IOException {
//            accountId_ = arg0.readLong();
//            creationDate_ = arg0.readLong();
//            deletionDate_ = arg0.readLong();
//            browserId_ = arg0.readInt();
//            country_ = arg0.readInt();
//            ipAddress_.readFields(arg0);
//            isLargePoster_ = arg0.readBoolean();
//        }
//
//        public void write(DataOutput arg0) throws IOException {
//            arg0.writeLong(accountId_);
//            arg0.writeLong(creationDate_);
//            arg0.writeLong(deletionDate_);
//            arg0.writeInt(browserId_);
//            arg0.writeInt(country_);
//            ipAddress_.write(arg0);
//            arg0.writeBoolean(isLargePoster_);
//        }
//    }


    public long accountId() {
        return accountId;
    }

    public void accountId(long id) {
        accountId = id;
    }

    public long creationDate() {
        return creationDate;
    }

    public long deletionDate() {
        return deletionDate;
    }

    public void creationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public void deletionDate(long deletionDate) {
        this.deletionDate = deletionDate;
    }

    public long maxNumKnows() {
        return maxNumKnows;
    }

    public void maxNumKnows(long maxKnows) {
        maxNumKnows = maxKnows;
    }

    public TreeSet<Knows> knows() {
        return knows;
    }

    public void knows(TreeSet<Knows> knows) {
        this.knows.clear();
        this.knows.addAll(knows);
    }

    public int browserId() {
        return browserId;
    }

    public void browserId(int browserId) {
        this.browserId = browserId;
    }

    public IP ipAddress() {
        return ipAddress;
    }

    public void ipAddress(IP ipAddress) {
        this.ipAddress.copy(ipAddress);
    }

    public int countryId() {
        return countryId;
    }

    public void countryId(int countryId) {
        this.countryId = countryId;
    }

    public int cityId() {
        return cityId;
    }

    public void cityId(int cityId) {
        this.cityId = cityId;
    }

    public long wallId() {
        return wallId;
    }

    public TreeSet<Integer> interests() {
        return interests;
    }

    public void interests(TreeSet<Integer> interests) {
        this.interests.clear();
        this.interests.addAll(interests);
    }

    public int mainInterest() {
        return mainInterest;
    }

    public void mainInterest(int interest) {
        mainInterest = interest;
    }

    public int universityLocationId() {
        return universityLocationId;
    }

    public void universityLocationId(int location) {
        universityLocationId = location;
    }

    public byte gender() {
        return gender;
    }

    public void gender(byte gender) {
        this.gender = gender;
    }

    public long birthday() {
        return birthday;
    }

    public void birthday(long birthday) {
        this.birthday = birthday;
    }

    public boolean isLargePoster() {
        return isLargePoster;
    }

    public void isLargePoster(boolean largePoster) {
        isLargePoster = largePoster;
    }

    public long randomId() {
        return randomId;
    }

    public void randomId(long randomId) {
        this.randomId = randomId;
    }

    public TreeSet<String> emails() {
        return emails;
    }

    public void emails(TreeSet<String> emails) {
        emails.clear();
        this.emails.addAll(emails);
    }

    public List<Integer> languages() {
        return languages;
    }

    public void languages(List<Integer> languages) {
        this.languages.clear();
        this.languages.addAll(languages);
    }

    public String firstName() {
        return firstName;
    }

    public void firstName(String firstName) {
        this.firstName = firstName;
    }

    public String lastName() {
        return lastName;
    }

    public void lastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<Long, Long> companies() {
        return companies;
    }

    public long classYear() {
        return classYear;
    }

    public void classYear(long classYear) {
        this.classYear = classYear;
    }

    public void readFields(DataInput arg0) throws IOException {
        accountId = arg0.readLong();
        creationDate = arg0.readLong();
        deletionDate = arg0.readLong();
        maxNumKnows = arg0.readLong();
        int numFriends = arg0.readShort();
        knows = new TreeSet<>();
        for (int i = 0; i < numFriends; i++) {
            Knows fr = new Knows();
            fr.readFields(arg0);
            knows.add(fr);
        }

        browserId = arg0.readInt();

        ipAddress.readFields(arg0);

        countryId = arg0.readInt();
        cityId = arg0.readInt();
        wallId = arg0.readLong();

        byte numTags = arg0.readByte();
        interests = new TreeSet<>();
        for (byte i = 0; i < numTags; i++) {
            interests.add(arg0.readInt());
        }
        mainInterest = arg0.readInt();

        universityLocationId = arg0.readInt();
        gender = arg0.readByte();
        birthday = arg0.readLong();
        isLargePoster = arg0.readBoolean();
        randomId = arg0.readLong();

        int numEmails = arg0.readInt();
        emails = new TreeSet<>();
        for (int i = 0; i < numEmails; ++i) {
            emails.add(arg0.readUTF());
        }
        int numLanguages = arg0.readInt();
        languages = new ArrayList<>();
        for (int i = 0; i < numLanguages; ++i) {
            languages.add(arg0.readInt());
        }
        firstName = arg0.readUTF();
        lastName = arg0.readUTF();
        int numCompanies = arg0.readInt();
        companies = new HashMap<>();
        for (int i = 0; i < numCompanies; ++i) {
            companies.put(arg0.readLong(), arg0.readLong());
        }
        classYear = arg0.readLong();
    }

    public void write(DataOutput arg0) throws IOException {
        arg0.writeLong(accountId);
        arg0.writeLong(creationDate);
        arg0.writeLong(deletionDate);
        arg0.writeLong(maxNumKnows);
        arg0.writeShort(knows.size());

        for (Knows f : knows) {
            f.write(arg0);
        }

        arg0.writeInt(browserId);
        ipAddress.write(arg0);

        arg0.writeInt(countryId);
        arg0.writeInt(cityId);
        arg0.writeLong(wallId);

        arg0.writeByte((byte) interests.size());
        Iterator<Integer> iter2 = interests.iterator();
        while (iter2.hasNext()) {
            arg0.writeInt(iter2.next());
        }
        arg0.writeInt(mainInterest);
        arg0.writeInt(universityLocationId);
        arg0.writeByte(gender);
        arg0.writeLong(birthday);
        arg0.writeBoolean(isLargePoster);
        arg0.writeLong(randomId);

        arg0.writeInt(emails.size());
        for (String s : emails) {
            arg0.writeUTF(s);
        }
        arg0.writeInt(languages.size());
        for (Integer l : languages) {
            arg0.writeInt(l);
        }
        arg0.writeUTF(firstName);
        arg0.writeUTF(lastName);
        arg0.writeInt(companies.size());
        for (Map.Entry<Long, Long> e : companies.entrySet()) {
            arg0.writeLong(e.getKey());
            arg0.writeLong(e.getValue());
        }
        arg0.writeLong(classYear);
    }

    public static PersonSimilarity personSimilarity;
}
