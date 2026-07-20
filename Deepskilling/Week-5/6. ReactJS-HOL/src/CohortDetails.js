import styles from './CohortDetails.module.css';

function CohortDetails({ cohort }) {
  const headingColor =
    cohort.status.toLowerCase() === 'ongoing' ? 'green' : 'blue';

  return (
    <article className={styles.box}>
      <h3 style={{ color: headingColor }}>
        {cohort.code} -{cohort.technology}
      </h3>
      <dl>
        <dt>Started On</dt>
        <dd>{cohort.startDate}</dd>
        <dt>Current Status</dt>
        <dd>{cohort.status}</dd>
        <dt>Coach</dt>
        <dd>{cohort.coach}</dd>
        <dt>Trainer</dt>
        <dd>{cohort.trainer}</dd>
      </dl>
    </article>
  );
}

export default CohortDetails;
