SELECT st.name, st.age, fa.name FROM student st JOIN faculty fa ON (st.faculty_id=fa.id);
SELECT * FROM student st JOIN avatar av ON (st.id =av.student_id);
