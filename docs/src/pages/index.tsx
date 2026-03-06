import clsx from 'clsx';
import Link from '@docusaurus/Link';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import Layout from '@theme/Layout';
import Heading from '@theme/Heading';

function Hero() {
  return (
    <header style={{
      background: 'linear-gradient(135deg, #0e0e3d 0%, #1A1A7D 100%)',
      padding: '80px 0',
      textAlign: 'center',
      color: 'white',
    }}>
      <div className="container">
        <Heading as="h1" style={{ fontSize: '3rem', fontWeight: 700, marginBottom: 16 }}>
          DeskUI
        </Heading>
        <p style={{ fontSize: '1.25rem', opacity: 0.85, maxWidth: 600, margin: '0 auto 32px' }}>
          Professional UI components for business and enterprise mobile applications.
          Built for Kotlin Compose and SwiftUI.
        </p>
        <div style={{ display: 'flex', gap: 12, justifyContent: 'center' }}>
          <Link className="button button--lg" to="/docs/getting-started"
            style={{ background: 'white', color: '#1A1A7D', fontWeight: 600, border: 'none' }}>
            Get Started
          </Link>
          <Link className="button button--lg button--outline" to="/docs/components/status-badge"
            style={{ color: 'white', borderColor: 'rgba(255,255,255,0.4)' }}>
            Components
          </Link>
        </div>
      </div>
    </header>
  );
}

function Features() {
  const features = [
    {
      title: 'Business-First Design',
      description: 'Purpose-built for ERP, CRM, and accounting apps. Status badges, data tables, filter chips, form renderers — not generic consumer UI.',
    },
    {
      title: 'Cross-Platform Native',
      description: 'Jetpack Compose for Android, SwiftUI for iOS. Same design language, native performance. No compromise.',
    },
    {
      title: 'Fully Themeable',
      description: 'Every color, size, shape, and spacing value is configurable through DeskTheme. Adapt to any brand in minutes.',
    },
    {
      title: 'Production-Ready',
      description: 'Extracted from a real production app. Battle-tested components that handle edge cases, loading states, and offline mode.',
    },
    {
      title: 'Data-Dense Layouts',
      description: 'Optimized for screens that show lots of information. Compact typography, efficient spacing, scrollable tables.',
    },
    {
      title: 'Zero Dependencies',
      description: 'No third-party UI libraries. Just Compose Material3 primitives on Android and native SwiftUI on iOS.',
    },
  ];

  return (
    <section style={{ padding: '64px 0' }}>
      <div className="container">
        <div className="row">
          {features.map((f, i) => (
            <div key={i} className="col col--4" style={{ marginBottom: 32 }}>
              <h3 style={{ fontSize: '1.1rem' }}>{f.title}</h3>
              <p style={{ color: 'var(--ifm-color-emphasis-700)', fontSize: '0.95rem' }}>{f.description}</p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}

function Comparison() {
  return (
    <section style={{ padding: '48px 0', background: 'var(--ifm-background-surface-color)' }}>
      <div className="container">
        <Heading as="h2" style={{ textAlign: 'center', marginBottom: 32 }}>Why not Material Design?</Heading>
        <div style={{ maxWidth: 700, margin: '0 auto' }}>
          <table style={{ width: '100%' }}>
            <thead>
              <tr><th>Material Design</th><th>DeskUI</th></tr>
            </thead>
            <tbody>
              <tr><td>Consumer-oriented, playful</td><td>Business-oriented, professional</td></tr>
              <tr><td>Generic components</td><td>Domain-specific: status badges, filter chips, data tables</td></tr>
              <tr><td>One-size-fits-all</td><td>Optimized for data-dense screens</td></tr>
              <tr><td>Looks like "Android" on iOS</td><td>Native feel on both platforms</td></tr>
              <tr><td>Heavy theming to look professional</td><td>Professional out of the box</td></tr>
            </tbody>
          </table>
        </div>
      </div>
    </section>
  );
}

export default function Home(): JSX.Element {
  return (
    <Layout title="Professional UI for Business Apps" description="DeskUI - Professional UI component library for Kotlin Compose and SwiftUI">
      <Hero />
      <main>
        <Features />
        <Comparison />
      </main>
    </Layout>
  );
}
